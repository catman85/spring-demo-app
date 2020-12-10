package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.view.dto.QuestionDto;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Override
    public List<QuestionDto> findAll() {
        List<Question> list = questionRepository.findAll();
        return list.stream()
                .map(this::convertFromEntityToDto).collect(Collectors.toList());
    }

    @Override
    public QuestionDto findById(Long id) {
        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
        return convertFromEntityToDto(q);
    }

    @Override
    public void save(QuestionDto questionDto) {
        Question q = new Question();
        User u = userRepository.findById(questionDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        q.setTitle(questionDto.getTitle());
        q.setDescription(questionDto.getDescription());
        q.setUser(u);
        questionRepository.save(q);
    }

    @Override
    public void delete(Long questionId) {
        Question q = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
        questionRepository.delete(q);
    }

    @Override
    public void update(Long questionId, QuestionDto questionDto) {
        Question q = questionRepository.findById(questionId)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
        User u = userRepository.findById(questionDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        q.setTitle(questionDto.getTitle());
        q.setDescription(questionDto.getDescription());
        q.setUser(u);
        questionRepository.save(q);
    }

    private QuestionDto convertFromEntityToDto(Question q) {
        Long userId = q.getUser().getId();
        return new QuestionDto(q.getId(), q.getTitle(), q.getDescription(), userId);
    }
}
