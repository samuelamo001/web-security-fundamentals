package com.springsecuritytutorial.tutorial;

import com.springsecuritytutorial.tutorial.security.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class WebSecurityFundamentals {

	public static void main(String[] args) {
		SpringApplication.run(WebSecurityFundamentals.class, args);
	}

}
