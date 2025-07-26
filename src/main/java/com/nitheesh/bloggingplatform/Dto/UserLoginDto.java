package com.nitheesh.bloggingplatform.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
