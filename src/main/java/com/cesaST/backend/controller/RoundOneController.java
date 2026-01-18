package com.cesaST.backend.controller;

import com.cesaST.backend.dto.SubmitRequest;
import com.cesaST.backend.model.McqQuestions;
import com.cesaST.backend.service.McqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/round1")
@RequiredArgsConstructor
@CrossOrigin
public class RoundOneController {

    private final McqService mcqService;

    @GetMapping("/questions")
    public List<McqQuestions> getQuestions() {
        return mcqService.getAllQuestions();
    }

    @PostMapping("/submit")
    public Result submit(@RequestBody SubmitRequest request) {
        int score = mcqService.evaluateAnswers(request.getAnswers());
        return new Result(score, request.getAnswers().size());
    }

    record Result(int score, int total) {}
}

