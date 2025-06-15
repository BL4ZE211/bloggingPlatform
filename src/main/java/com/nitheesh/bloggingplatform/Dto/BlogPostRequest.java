package com.nitheesh.bloggingplatform.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogPostRequest {
    private String title;
    private String content;
    private String status;

}
