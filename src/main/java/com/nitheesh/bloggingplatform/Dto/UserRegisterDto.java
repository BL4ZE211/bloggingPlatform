package com.nitheesh.bloggingplatform.Dto;

import com.nitheesh.bloggingplatform.annotation.UniqueEmail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    @NotBlank(message = "name should not be empty")
    private String name;

    @UniqueEmail
    @NotBlank(message = "email should not be empty")
    private String email;

    @Size(min = 6,max = 12,message = "password's length should be between 6 and 12")
    @NotBlank(message = "password should not be empty")
    private String password;

    @NotBlank(message = "role should not be empty")
    @Schema(description = "can have roles like user , author , editor and admin")
    private String role;
}
