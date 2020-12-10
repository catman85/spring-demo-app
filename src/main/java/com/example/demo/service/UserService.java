package com.example.demo.service;

import com.example.demo.view.dto.UserDto;

import java.util.List;

public interface UserService {
    public List<UserDto> findAll();
}
