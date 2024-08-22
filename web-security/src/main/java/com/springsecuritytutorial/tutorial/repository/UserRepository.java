package com.springsecuritytutorial.tutorial.repository;

import com.springsecuritytutorial.tutorial.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByUsername(String username);
}
