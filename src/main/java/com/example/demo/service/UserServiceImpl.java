package com.example.demo.service;

import com.example.demo.dto.user.UserRequestDto;
import com.example.demo.dto.user.UserResponseDto;
import com.example.demo.model.Account;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.MyHelper;

import org.springframework.security.access.prepost.PreAuthorize;
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
    private final MyHelper myHelper;
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

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long userId) {
        User u = userRepository.getOne(userId);
        userRepository.delete(u);
    }

    @Override
    @Transactional
    public void update(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.getOne(userId);
        myHelper.isRequestMadeByUser(user);
        Account account = user.getAccount();

        user.setName(userRequestDto.getName());
        user.setSurname(userRequestDto.getSurname());
        account.setUsername(userRequestDto.getUsername());
        account.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        account.setRole(userRequestDto.getGrantedAuthorities());

        userRepository.save(user);

    }
}
