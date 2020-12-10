package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.view.dto.QuestionDto;

import java.util.List;

public interface QuestionService {
    public List<QuestionDto> findAll();
    public QuestionDto findById(Long id);
    public void save(QuestionDto questionDto);
    public void delete(Long questionId);
    public void update(Long questionId, QuestionDto questionDto);
}
