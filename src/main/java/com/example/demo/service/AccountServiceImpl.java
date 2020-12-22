package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> selectApplicationUserByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

}
