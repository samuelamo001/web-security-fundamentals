package com.springsecuritytutorial.tutorial.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ViewController {


        @GetMapping("/profile")
        public String profile(OAuth2AuthenticationToken token, Model model) {
                model.addAttribute("name", token.getPrincipal().getAttribute("name"));
                model.addAttribute("email", token.getPrincipal().getAttribute("email"));
                model.addAttribute("photo", token.getPrincipal().getAttribute("picture"));
                return "user-profile";
        }


        @GetMapping("/login")
        public String login() {

                return "custom_login";
        }

}
