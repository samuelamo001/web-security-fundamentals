package com.springsecuritytutorial.tutorial.security.jwt;

public class JwtTokenUtil {
    public static String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        throw new IllegalArgumentException("Invalid Authorization header.");
    }
}
