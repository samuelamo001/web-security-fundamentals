package com.springsecuritytutorial.tutorial.service;
import com.springsecuritytutorial.tutorial.dto.LoginRequest;
import com.springsecuritytutorial.tutorial.dto.RegisterRequest;
import com.springsecuritytutorial.tutorial.dto.Response;
import com.springsecuritytutorial.tutorial.model.AppUser;
import com.springsecuritytutorial.tutorial.repository.UserRepository;
import com.springsecuritytutorial.tutorial.security.service.AuthenticatedUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public Response register(RegisterRequest request) {

        AppUser newUser = new AppUser();
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepository.save(newUser);

        return Response.builder().status("registration successful").build();

    }

    public Response login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));


        AuthenticatedUserDetails authenticatedUser = (AuthenticatedUserDetails) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> authenticatedUserAuthority = authentication.getAuthorities();



        return Response.builder().success("login successful").status(String.valueOf(authenticatedUserAuthority)).build();
    }
}
