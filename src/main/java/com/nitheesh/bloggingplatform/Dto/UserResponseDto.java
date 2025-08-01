package com.nitheesh.bloggingplatform.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private long id;
    private String name;
    private String email;
    private String role;
}
