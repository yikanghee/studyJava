package com.example.demo.controller;

import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BasicController {

    private final TestRepository testRepository;

    @GetMapping
    public ResponseEntity<String> test(@RequestParam Test test) {

        Optional<Test> save = testRepository.findById(test.getId());

        return ResponseEntity.ok().body("안녕");
    }


}
