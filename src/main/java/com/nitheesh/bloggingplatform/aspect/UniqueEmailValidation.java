package com.nitheesh.bloggingplatform.aspect;

import com.nitheesh.bloggingplatform.annotation.UniqueEmail;
import com.nitheesh.bloggingplatform.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueEmailValidation implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
       return !userRepository.existsByEmail(email);
    }
}
