package com.example.demo.controller;

import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.QuestionService;
import com.example.demo.util.MyHelper;
import com.example.demo.view.dto.QuestionDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor // args need to be final
@Log
public class QuestionController {

    // TODO caches
    // TODO security
    // TODO test
    // TODO postman auth script
    // TODO Docker

    private final QuestionService questionService;
    private final MyHelper myHelper;

    @GetMapping(produces = "application/json")
    public Page<QuestionDto> getQuestions(Pageable pageable) {
        return myHelper.convertListToPage(questionService.findAll(), pageable);
    }

    @GetMapping("/{questionId}")
    public QuestionDto getQuestions(@PathVariable Long questionId) {
        return questionService.findById(questionId);
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDto question) {
        questionService.save(question);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{questionId}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long questionId, @Valid @RequestBody QuestionDto questionRequest) {
        try {
            questionService.update(questionId, questionRequest);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.warning("create question error");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
        questionService.delete(questionId);
        return ResponseEntity.ok().build();
    }
}
