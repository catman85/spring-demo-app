package com.example.demo.service;

import com.example.demo.model.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> selectApplicationUserByUsername(String username);
}
