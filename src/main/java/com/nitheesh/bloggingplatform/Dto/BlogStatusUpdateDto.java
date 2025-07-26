package com.nitheesh.bloggingplatform.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogStatusUpdateDto {
    @NotEmpty
    private  String status;
}
