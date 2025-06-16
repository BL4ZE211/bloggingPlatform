package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.BlogPostRequest;
import com.nitheesh.bloggingplatform.Dto.BlogPostResponse;
import com.nitheesh.bloggingplatform.Dto.BlogStatusUpdateDto;
import com.nitheesh.bloggingplatform.entity.BlogPosts;
import com.nitheesh.bloggingplatform.service.BlogsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostsController {

    @Autowired
    private BlogsService blogsService;

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping
    public BlogPostResponse createPost(@RequestBody BlogPostRequest blogPostRequest, HttpServletRequest request){
        System.out.println("Triggered");
        return blogsService.post(blogPostRequest,request);
    }

    @GetMapping
    public List<BlogPosts> getAllPosts(){
        return blogsService.getAllPosts();
    }

    @GetMapping("/{id}")
    public  BlogPostResponse getPostById(@PathVariable long id){
        return blogsService.getPostByid(id);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    public BlogPostResponse updatePost(@RequestBody BlogPostRequest blogPostRequest,@PathVariable long id){
        return blogsService.updatePost(blogPostRequest,id);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('EDITOR')")
    public  BlogPostResponse updateStatus(@RequestBody BlogStatusUpdateDto blogStatusUpdateDto,@PathVariable long id ){
        return blogsService.updateStatus(blogStatusUpdateDto,id);
    }
}
