package com.nitheesh.bloggingplatform.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostResponse {
    private Long postId;
    private String title;
    private String content;
    private String status;
    private String authorName;
    private Timestamp createdAt;
}
