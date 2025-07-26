package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.service.BlogsService;
import com.nitheesh.bloggingplatform.service.CommnetsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Tag(
        name = "Admin Operations",
        description = "Endpoints for administrative actions such as deleting posts and comments. Restricted to users with ADMIN role."
)
public class AdminController {

    @Autowired
    private BlogsService blogsService;

    @Autowired
    private CommnetsService commnetsService;

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a blog post",
            description = "Permanently deletes a blog post by its ID. Only accessible to administrators. This operation cannot be undone."
    )
    public ResponseEntity<String> deletepostById(@PathVariable long id){
        blogsService.deletePostById(id);
        return new ResponseEntity<>("Post deleted",HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
            summary = "Delete a comment",
            description = "Permanently deletes a comment by its ID. Accessible only to administrators or moderators. This action is irreversible."
    )
    public ResponseEntity<String> deleteCommentById(@PathVariable long id){
        commnetsService.deleteCommentByid(id);
        return new ResponseEntity<>("Comment deleted",HttpStatus.NO_CONTENT);
    }

}
