package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.CommentRequest;
import com.nitheesh.bloggingplatform.Dto.CommentResponse;
import com.nitheesh.bloggingplatform.entity.Comments;
import com.nitheesh.bloggingplatform.service.CommnetsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Tag(name = "Comments", description = "Endpoints for creating and retrieving comments on blog posts.")
public class CommentsController {

    @Autowired
    private CommnetsService commnetsService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','AUTHOR','EDITOR')")
    @Operation(
            summary = "Create a new comment",
            description = "Creates a new comment on a blog post. Requires the post ID and comment content. This endpoint is accessible to all authenticated users."
    )

    public ResponseEntity<CommentResponse> createComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request){
        CommentResponse comment = commnetsService.createComment(commentRequest, request);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('USER','AUTHOR','EDITOR')")
    @GetMapping
    @Operation(
            summary = "Retrieve all comments",
            description = "Returns a list of all comments across all blog posts. This endpoint is publicly accessible and does not require authentication."
    )
    public ResponseEntity<List<CommentResponse>> getAllComments(){
        List<CommentResponse> allComments = commnetsService.getAllComments();
        return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a comment by ID",
            description = "Fetches the details of a specific comment using its unique identifier. Returns 404 if the comment does not exist or has been deleted. Accessible to all roles."
    )
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable long id){
        CommentResponse commentResponse =   commnetsService.getCommentById(id);
        return  new ResponseEntity<>(commentResponse,HttpStatus.OK);
    }



}
