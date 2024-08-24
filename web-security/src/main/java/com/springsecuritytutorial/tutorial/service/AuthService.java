package com.springsecuritytutorial.tutorial.service;

import com.springsecuritytutorial.tutorial.dto.LoginRequest;
import com.springsecuritytutorial.tutorial.dto.RegisterRequest;
import com.springsecuritytutorial.tutorial.dto.Response;
import com.springsecuritytutorial.tutorial.exception.AuthenticationFailedException;
import com.springsecuritytutorial.tutorial.exception.UserAlreadyExistException;
import com.springsecuritytutorial.tutorial.security.jwt.JwtService;
import com.springsecuritytutorial.tutorial.model.AppUser;
import com.springsecuritytutorial.tutorial.repository.UserRepository;
import com.springsecuritytutorial.tutorial.security.service.AuthenticatedUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    public Response register(RegisterRequest request) {



        AppUser newUser = new AppUser();
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            throw new UserAlreadyExistException("User with name " + request.getUsername() + " already exists");
        }

        userRepository.save(newUser);

        return Response.builder().status("registration successful").build();

    }

    public Response login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        AuthenticatedUserDetails user = (AuthenticatedUserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user.appUser());

        return Response.builder().status("login successful").token(token).build();
    }

}
