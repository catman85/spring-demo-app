package com.example.demo.service;

import com.example.demo.dto.user.UserRequestDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDto> userResponseDtoList;
        userResponseDtoList = userList.stream()
                .map(u -> {
                    Long amountQuestions = (long) u.getQuestionList().size();
                    return new UserResponseDto(u.getId(), u.getName(), u.getSurname(), amountQuestions);
                }).collect(Collectors.toList());

        return userResponseDtoList;
    }

    @Override
    @Transactional
    public void newUser(UserRequestDto userRequestDto) {
        User newUser = new User();
        Account newAccount = new Account();
        // If we use the builder pattern here the default values
        // from the entity won't be assigned
        newAccount.setUsername(userRequestDto.getUsername());
        newAccount.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        newAccount.setRole(userRequestDto.getGrantedAuthorities());
        newAccount.setUser(newUser);

        newUser.setName(userRequestDto.getName());
        newUser.setSurname(userRequestDto.getSurname());
        newUser.setAccount(newAccount);
        userRepository.save(newUser);
    }
}
