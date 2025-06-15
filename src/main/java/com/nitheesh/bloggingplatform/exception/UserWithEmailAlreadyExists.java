package com.nitheesh.bloggingplatform.exception;

public class UserWithEmailAlreadyExists extends RuntimeException{
    public UserWithEmailAlreadyExists(String message) {
        super(message);
    }
}
