package com.springsecuritytutorial.tutorial.controller;

import com.springsecuritytutorial.tutorial.dto.LoginRequest;
import com.springsecuritytutorial.tutorial.dto.RegisterRequest;
import com.springsecuritytutorial.tutorial.dto.Response;
import com.springsecuritytutorial.tutorial.model.AppUser;
import com.springsecuritytutorial.tutorial.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest register) {
        return ResponseEntity.ok(authService.register(register));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
