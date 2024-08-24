package com.springsecuritytutorial.tutorial.exception;

public class AuthenticationFailedException extends RuntimeException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
