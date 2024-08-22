package com.springsecuritytutorial.tutorial.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "user name must be between 3 to 20 characters")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should be at least 8 characters long")
    private String password;

}
