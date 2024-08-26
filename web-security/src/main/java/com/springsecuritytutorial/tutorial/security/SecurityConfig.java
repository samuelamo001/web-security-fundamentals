package com.springsecuritytutorial.tutorial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        configureCsrf(http);
        configureAuthorization(http);
        configureSessionManagement(http);
        configureOAuth2Login(http);

        return http.build();

    }



    private void configureCsrf(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
    }

    private void configureAuthorization(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated());
    }

    private void configureSessionManagement(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement -> sessionManagement
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true));
    }

    private void configureOAuth2Login(HttpSecurity http) throws Exception {
        http.oauth2Login(oauth2Login -> oauth2Login
                .loginPage("/login")
                .successHandler((request, response, authentication) -> response.sendRedirect("/profile"))
                .failureUrl("/login?error"));
    }
}
