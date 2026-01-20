package com.cesaST.backend.controller;

import com.cesaST.backend.dto.SubmitRequest;
import com.cesaST.backend.model.McqQuestions;
import com.cesaST.backend.service.McqService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/round1")
@RequiredArgsConstructor
@CrossOrigin
public class RoundOneController {

    private final McqService mcqService;

//    ################ INTERNAL ####################

    @PreAuthorize("hasRole('INTERNAL')")
    @GetMapping("/questions")
    public List<McqQuestions> getQuestions() {
        return mcqService.getAllQuestions();
    }

    @PreAuthorize("hasRole('INTERNAL')")
    @PostMapping("/questions")
    public McqQuestions create(@RequestBody McqQuestions q) {
        return mcqService.createQuestion(q);
    }

    @PreAuthorize("hasRole('INTERNAL')")
    @PutMapping("/questions/{id}")
    public McqQuestions update(@PathVariable Long id,
                               @RequestBody McqQuestions q) {
        return mcqService.updateQuestion(id, q);
    }

    @PreAuthorize("hasRole('INTERNAL')")
    @DeleteMapping("/questions/{id}")
    public void delete(@PathVariable Long id) {
        mcqService.deleteQuestion(id);
    }

    //    ################ PUBLIC ####################

    @PostMapping("/submit")
    public Result submit(@RequestBody SubmitRequest request) {
        int score = mcqService.evaluateAnswers(request.getAnswers());
        return new Result(score, request.getAnswers().size());
    }

    record Result(int score, int total) {}
}


