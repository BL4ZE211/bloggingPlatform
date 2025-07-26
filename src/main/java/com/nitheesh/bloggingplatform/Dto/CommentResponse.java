package com.nitheesh.bloggingplatform.Dto;

import com.nitheesh.bloggingplatform.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private String content;
    private long postId;
    private long UserId;
    private Timestamp createdAt;


}
