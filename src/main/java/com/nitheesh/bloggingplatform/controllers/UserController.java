package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.UserAuthDto;
import com.nitheesh.bloggingplatform.Dto.UserLoginDto;
import com.nitheesh.bloggingplatform.Dto.UserRegisterDto;
import com.nitheesh.bloggingplatform.Dto.UserResponseDto;
import com.nitheesh.bloggingplatform.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(name = "User Api's",description = "can register and login")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "User Registration",description = "This endpoint allows a new user to register by providing their basic details (e.g., name, email, password). On successful registration, a user account is created and saved to the database. It may return a success message or the created user object (excluding sensitive data like password).")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody  UserRegisterDto userRegisterDto){
        UserResponseDto register = userService.register(userRegisterDto);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login",
    description = "This endpoint authenticates the user based on the provided username/email and password. " +
            "If the credentials are valid, it returns a JWT token which should be used in subsequent requests for authorization.")
    public ResponseEntity<UserAuthDto> login(@RequestBody UserLoginDto userLoginDto){
        UserAuthDto login = userService.login(userLoginDto);
        return  new ResponseEntity<>(login,HttpStatus.OK);
    }
}
