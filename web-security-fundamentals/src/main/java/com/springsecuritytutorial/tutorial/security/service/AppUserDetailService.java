package com.springsecuritytutorial.tutorial.security.service;

import com.springsecuritytutorial.tutorial.model.AppUser;
import com.springsecuritytutorial.tutorial.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username).orElseThrow();

        return new AuthenticatedUserDetails(user);
    }
}
