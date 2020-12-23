package com.example.demo.controller;

import com.example.demo.dto.user.UserRequestDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.service.UserService;
import com.example.demo.util.MyHelper;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@Log
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MyHelper myHelper;

    @GetMapping(path = "/users", produces = "application/json")
    @Cacheable("users")
    public Page<UserResponseDto> getUsers(Pageable pageable) {
        List<UserResponseDto> list = userService.findAll();
        return myHelper.convertListToPage(list, pageable);
    }

    @PostMapping("/register")
    @CacheEvict(value = "users", allEntries = true)
    public void newUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        log.fine("New User");
        userService.newUser(userRequestDto);
    }

    @PutMapping("/users/{userId}")
    @CacheEvict(value = "users", allEntries = true)
    public void updateUser(@PathVariable Long userId, @RequestBody @Valid UserRequestDto userRequestDto) {
        userService.update(userId, userRequestDto);
    }

    @DeleteMapping("/users/{userId}")
    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
