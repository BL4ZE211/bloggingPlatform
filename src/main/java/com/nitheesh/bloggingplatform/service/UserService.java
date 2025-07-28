package com.nitheesh.bloggingplatform.service;

import com.nitheesh.bloggingplatform.Dto.UserAuthDto;
import com.nitheesh.bloggingplatform.Dto.UserLoginDto;
import com.nitheesh.bloggingplatform.Dto.UserRegisterDto;
import com.nitheesh.bloggingplatform.Dto.UserResponseDto;
import com.nitheesh.bloggingplatform.annotation.LogExecutionTime;
import com.nitheesh.bloggingplatform.config.SecurityConfig;
import com.nitheesh.bloggingplatform.entity.Users;
import com.nitheesh.bloggingplatform.exception.ResourceNotFoundException;
import com.nitheesh.bloggingplatform.exception.UserWithEmailAlreadyExists;
import com.nitheesh.bloggingplatform.repository.UserRepository;
import com.nitheesh.bloggingplatform.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResponseDto mapToDto(Users users){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setName(users.getName());
        userResponseDto.setId(users.getId());
        userResponseDto.setEmail(users.getEmail());
        userResponseDto.setRole(users.getRole());

        return userResponseDto;
    }

    @LogExecutionTime
    public UserResponseDto register(UserRegisterDto userRegisterDto) {
        if(userRepository.existsByEmail(userRegisterDto.getEmail())){
            throw new UserWithEmailAlreadyExists("User with email "+userRegisterDto.getEmail()+" exists");
        }

        Users users = new Users();
        users.setName(userRegisterDto.getName());
        users.setEmail(userRegisterDto.getEmail());
        users.setRole(userRegisterDto.getRole());
        users.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        userRepository.save(users);

        return mapToDto(users);
    }
    @LogExecutionTime
    public UserAuthDto login(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),userLoginDto.getPassword())
        );
        Users user = userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(
                ()-> new ResourceNotFoundException("No user with email : "+ userLoginDto.getEmail()));

        String token = jwtUtil.generateToken(userLoginDto.getEmail(), user.getRole());
        UserAuthDto userAuthDto= new UserAuthDto();
        userAuthDto.setToken(token);
        userAuthDto.setRole(user.getRole());
        return userAuthDto;

    }
}
