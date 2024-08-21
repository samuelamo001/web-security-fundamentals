package com.springsecuritytutorial.tutorial.model;


import jakarta.persistence.*;
import lombok.*;


@Data
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
