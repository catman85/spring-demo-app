package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.util.MyHelper;
import com.example.demo.view.dto.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public Page<UserDto> getQuestions(Pageable pageable) {
        log.fine("User Controller");
        List<UserDto> list = userService.findAll();
        return myHelper.convertListToPage(list, pageable);
    }

}
