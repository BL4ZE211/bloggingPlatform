package com.nitheesh.bloggingplatform.security;

import com.nitheesh.bloggingplatform.entity.Users;
import com.nitheesh.bloggingplatform.exception.ResourceNotFoundException;
import com.nitheesh.bloggingplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)  {
        Users user = userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("No user With email : "+ email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().toUpperCase())
                .build();
    }
}
