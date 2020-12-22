package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt, will internally generate a random salt.
        // BCrypt will store the salt inside the hash value itself.
        // It will generate a string of length 60
        return new BCryptPasswordEncoder(10);
    }
}