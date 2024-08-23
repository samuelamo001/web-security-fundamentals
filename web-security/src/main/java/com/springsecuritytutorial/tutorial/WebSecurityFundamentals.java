package com.springsecuritytutorial.tutorial;

import com.springsecuritytutorial.tutorial.security.configuration.RsaKeyProperties;
import com.springsecuritytutorial.tutorial.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class WebSecurityFundamentals {

	public static void main(String[] args) {
		SpringApplication.run(WebSecurityFundamentals.class, args);
	}

}
