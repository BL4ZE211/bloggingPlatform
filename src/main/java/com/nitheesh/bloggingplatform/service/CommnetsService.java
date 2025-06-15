package com.nitheesh.bloggingplatform.service;

import com.nitheesh.bloggingplatform.Dto.CommentRequest;
import com.nitheesh.bloggingplatform.Dto.CommentResponse;
import com.nitheesh.bloggingplatform.entity.BlogPosts;
import com.nitheesh.bloggingplatform.entity.Comments;
import com.nitheesh.bloggingplatform.entity.Users;
import com.nitheesh.bloggingplatform.exception.ResourceNotFoundException;
import com.nitheesh.bloggingplatform.repository.BlogPostsRepository;
import com.nitheesh.bloggingplatform.repository.CommentsRepository;
import com.nitheesh.bloggingplatform.repository.UserRepository;
import com.nitheesh.bloggingplatform.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommnetsService {

    @Autowired
    private BlogPostsRepository blogPostsRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public CommentResponse createComment(CommentRequest commentRequest, HttpServletRequest request) {
        BlogPosts blogPosts = blogPostsRepository.findById(commentRequest.getPostId()).orElseThrow(
                ()-> new ResourceNotFoundException("No post with post id : "+ commentRequest.getPostId()));

        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String email = jwtUtil.getEmail(token);



        Users users = userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("No user found"));

        Comments comments = new Comments();
        comments.setContent(commentRequest.getContent());
        comments.setPosts(blogPosts);
        comments.setUser(users);

        commentsRepository.save(comments);

        return mapToDto(comments);

    }

    private CommentResponse mapToDto(Comments comments) {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(comments.getContent());
        commentResponse.setPostId(comments.getPosts().getId());
        commentResponse.setUserId(comments.getUser().getId());
        commentResponse.setCreatedAt(comments.getCreatedAt());

        return commentResponse;
    }

    public List<Comments> getAllComments() {
        return commentsRepository.findAll();
    }

    public CommentResponse getCommentById(long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No comment with id : "+ id));

        return mapToDto(comments);
    }

    public void deleteCommentByid(long id) {
        Comments comments = commentsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No comment with id : "+ id));

        commentsRepository.deleteById(id);
    }
}
