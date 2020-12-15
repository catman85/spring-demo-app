package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.util.MyHelper;
import com.example.demo.view.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/users")
@Log
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MyHelper myHelper;

    @GetMapping(produces = "application/json")
    @Cacheable("users")
    public Page<UserDto> getUsers(Pageable pageable) {
        log.fine("Get Users Controller");
        List<UserDto> list = userService.findAll();
        return myHelper.convertListToPage(list, pageable);
    }

    @PostMapping
    @CacheEvict(value = "users", allEntries = true)
    public void newUser(@RequestBody @Valid UserDto userDto){
        userService.newUser(userDto);
    }

}
