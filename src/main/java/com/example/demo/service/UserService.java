package com.example.demo.service;

import com.example.demo.dto.user.UserRequestDto;
import com.example.demo.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    public List<UserResponseDto> findAll();
    public void newUser(UserRequestDto userRequestDto);
    public void delete(Long userId);
    public void update(Long userId, UserRequestDto userRequestDto);
}
