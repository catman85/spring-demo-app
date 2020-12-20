package com.example.demo.service;

import com.example.demo.view.dto.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
    public void newUser(UserDto userDto);
}
