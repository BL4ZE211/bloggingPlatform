package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.service.BlogsService;
import com.nitheesh.bloggingplatform.service.CommnetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private BlogsService blogsService;

    @Autowired
    private CommnetsService commnetsService;

    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletepostById(@PathVariable long id){
        blogsService.deletePostById(id);
    }

    @DeleteMapping("/comments/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCommentById(@PathVariable long id){
        commnetsService.deleteCommentByid(id);
    }

}
