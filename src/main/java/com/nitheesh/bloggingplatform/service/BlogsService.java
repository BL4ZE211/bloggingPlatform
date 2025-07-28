package com.nitheesh.bloggingplatform.service;

import com.nitheesh.bloggingplatform.Dto.BlogPostRequest;
import com.nitheesh.bloggingplatform.Dto.BlogPostResponse;
import com.nitheesh.bloggingplatform.Dto.BlogStatusUpdateDto;
import com.nitheesh.bloggingplatform.Dto.UserResponseDto;
import com.nitheesh.bloggingplatform.entity.BlogPosts;
import com.nitheesh.bloggingplatform.entity.Users;
import com.nitheesh.bloggingplatform.exception.ResourceNotFoundException;
import com.nitheesh.bloggingplatform.repository.BlogPostsRepository;
import com.nitheesh.bloggingplatform.repository.UserRepository;
import com.nitheesh.bloggingplatform.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogsService {

    @Autowired
    private BlogPostsRepository blogPostsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public BlogPostResponse mapToDto(BlogPosts blogPosts){
        BlogPostResponse blogPostResponse = new BlogPostResponse();
        blogPostResponse.setTitle(blogPosts.getTitle());
        blogPostResponse.setContent(blogPosts.getContent());
        blogPostResponse.setPostId(blogPosts.getId());
        blogPostResponse.setAuthorName(blogPosts.getAuthor().getName());
        blogPostResponse.setStatus(blogPosts.getStatus());
        blogPostResponse.setCreatedAt(blogPosts.getCreatedAt());
        return blogPostResponse;
    }


    public BlogPostResponse post(BlogPostRequest blogPostRequest, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(7);
        String email = jwtUtil.getEmail(token);



        Users author = userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("No user found"));

        BlogPosts blogPosts = new BlogPosts();
        blogPosts.setTitle(blogPostRequest.getTitle());
        blogPosts.setContent(blogPostRequest.getContent());
        blogPosts.setAuthor(author);
        blogPosts.setStatus(blogPostRequest.getStatus());

        blogPostsRepository.save(blogPosts);

        return  mapToDto(blogPosts);
    }

    public List<BlogPostResponse> getAllPosts() {
        return blogPostsRepository.findAll()
                .stream().map(post -> BlogPostResponse.builder()
                        .postId(post.getId())
                        .status(post.getStatus())
                        .createdAt(post.getCreatedAt())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .authorName(post.getAuthor().getName())
                        .build())
                .collect(Collectors.toList());
    }

    public BlogPostResponse getPostByid(long id) {
        BlogPosts blogPosts = blogPostsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No post with id : "+ id));
        return mapToDto(blogPosts);
    }

    public void deletePostById(long id) {
        BlogPosts blogPosts = blogPostsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No post with id : "+ id));

        blogPostsRepository.deleteById(id);
    }

    public BlogPostResponse updatePost(BlogPostRequest blogPostRequest,long id) {
        BlogPosts existingblogPosts = blogPostsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No post with id : "+ id));

        existingblogPosts.setTitle(blogPostRequest.getTitle());
        existingblogPosts.setContent(blogPostRequest.getContent());

        return mapToDto(existingblogPosts);

    }


    public BlogPostResponse updateStatus(BlogStatusUpdateDto blogStatusUpdateDto,long id) {
        BlogPosts existingblogPosts = blogPostsRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No post with id : "+ id));
        existingblogPosts.setStatus(blogStatusUpdateDto.getStatus());
        blogPostsRepository.save(existingblogPosts);

        return mapToDto(existingblogPosts);
    }
}
