package com.nitheesh.bloggingplatform.controllers;

import com.nitheesh.bloggingplatform.Dto.UserAuthDto;
import com.nitheesh.bloggingplatform.Dto.UserLoginDto;
import com.nitheesh.bloggingplatform.Dto.UserRegisterDto;
import com.nitheesh.bloggingplatform.Dto.UserResponseDto;
import com.nitheesh.bloggingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody UserRegisterDto userRegisterDto){
        return userService.register(userRegisterDto);
    }

    @PostMapping("/login")
    public UserAuthDto login(@RequestBody UserLoginDto userLoginDto){
        return userService.login(userLoginDto);
    }
}
