package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.view.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList;
        userDtoList = userList.stream()
                .map(u -> {
                    Long amountQuestions = (long) u.getQuestionList().size();
                    return new UserDto(u.getId(), u.getName(), u.getSurname(), amountQuestions);
                }).collect(Collectors.toList());

        return userDtoList;
    }

    @Override
    public void newUser(UserDto userDto) {
        User newUser = new User();
        newUser.setName(userDto.getName());
        newUser.setSurname(userDto.getSurname());
        userRepository.save(newUser);
    }
}
