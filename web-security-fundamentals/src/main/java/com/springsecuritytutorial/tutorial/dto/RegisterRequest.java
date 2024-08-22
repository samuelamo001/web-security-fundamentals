package com.springsecuritytutorial.tutorial.dto;

import com.springsecuritytutorial.tutorial.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Integer id;
    @NotBlank(message = "first name is required")
    @Size(min = 3, max = 20, message = "first name must be between 3 to 20 characters")
    private String firstname;

    @NotBlank(message = "last name is required")
    @Size(min = 3, max = 20, message = "first name must be between 3 to 20 characters")
    private String lastname;

    @NotBlank(message = "username is required")
    @Size(min = 3, max = 20, message = "user name must be between 3 to 20 characters")
    private String username;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "password should be at least 8 characters long")
    private String password;

    @NotNull(message = "role is required")
    private Role role;
}
