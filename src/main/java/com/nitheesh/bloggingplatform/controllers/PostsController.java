package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.BlogPostRequest;
import com.nitheesh.bloggingplatform.Dto.BlogPostResponse;
import com.nitheesh.bloggingplatform.Dto.BlogStatusUpdateDto;
import com.nitheesh.bloggingplatform.entity.BlogPosts;
import com.nitheesh.bloggingplatform.repository.BlogPostsRepository;
import com.nitheesh.bloggingplatform.service.BlogsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Posts", description = "Endpoints for creating, updating, retrieving, and managing blog posts.")
public class PostsController {

    @Autowired
    private BlogsService blogsService;

    @PreAuthorize("hasRole('AUTHOR')")
    @PostMapping
    @Operation(
            summary = "Create a new blog post",
            description = "Creates a new blog post with the provided title, content, and optional tags or metadata. Requires authentication and appropriate role (e.g., 'AUTHOR' or 'ADMIN')."
    )
    public ResponseEntity<BlogPostResponse> createPost(@RequestBody BlogPostRequest blogPostRequest, HttpServletRequest request){
        BlogPostResponse post = blogsService.post(blogPostRequest, request);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Retrieve all blog posts",
            description = "Returns a list of all blog posts. "
    )
    public ResponseEntity<List<BlogPostResponse>> getAllPosts(){
        List<BlogPostResponse>  allBlogs =  blogsService.getAllPosts();
        return new ResponseEntity<>(allBlogs,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a blog post by ID",
            description = "Retrieves a single blog post by its unique identifier. Returns 404 if the post does not exist or access is restricted."
    )
    public  ResponseEntity<BlogPostResponse> getPostById(@PathVariable long id){

        BlogPostResponse blogPostResponse =  blogsService.getPostByid(id);
        return new ResponseEntity<>(blogPostResponse,HttpStatus.OK);
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('AUTHOR')")
    @Operation(
            summary = "Update blog post content",
            description = "Updates the title and/or content of an existing post. Requires authentication and ownership or appropriate role. The post ID must exist; otherwise, returns 404."
    )
    public ResponseEntity<BlogPostResponse> updatePost(@RequestBody BlogPostRequest blogPostRequest,@PathVariable long id){
        BlogPostResponse blogPostResponse =  blogsService.updatePost(blogPostRequest,id);
        return new ResponseEntity<>(blogPostResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('EDITOR')")
    @Operation(
            summary = "Update blog post status",
            description = "Updates the publication status of the blog post (e.g., draft to published). This operation is typically restricted to editors."
    )
    public  ResponseEntity<BlogPostResponse> updateStatus(@RequestBody BlogStatusUpdateDto blogStatusUpdateDto,@PathVariable long id ){
        BlogPostResponse blogPostResponse =  blogsService.updateStatus(blogStatusUpdateDto,id);
        return new ResponseEntity<>(blogPostResponse,HttpStatus.OK);
    }

}
