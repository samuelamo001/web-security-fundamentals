package com.springsecuritytutorial.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExist(UserAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<?> handleAuthenticationFailed(AuthenticationFailedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ResponseEntity<Object> handleUnauthorizedAccess(UnauthorizedAccessException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("{\"message\":\"" + ex.getMessage() + "\"}");
    }
}
