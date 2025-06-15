package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.CommentRequest;
import com.nitheesh.bloggingplatform.Dto.CommentResponse;
import com.nitheesh.bloggingplatform.entity.Comments;
import com.nitheesh.bloggingplatform.service.CommnetsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommnetsService commnetsService;

    @PostMapping
    public CommentResponse createComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request){
        return  commnetsService.createComment(commentRequest,request);
    }

    @GetMapping
    public List<Comments> getAllComments(){
        return commnetsService.getAllComments();
    }

    @GetMapping("/{id}")
    public CommentResponse getCommentById(@PathVariable long id){
        return  commnetsService.getCommentById(id);
    }



}
