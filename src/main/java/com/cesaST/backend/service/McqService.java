package com.cesaST.backend.service;

import com.cesaST.backend.dto.AnswerRequest;
import com.cesaST.backend.model.McqQuestions;
import com.cesaST.backend.repo.McqQuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class McqService {

    private final McqQuestionsRepository repository;

    public List<McqQuestions> getAllQuestions() {
        return repository.findAll();
    }

    public int evaluateAnswers(List<AnswerRequest> answers) {
        int score = 0;

        for (AnswerRequest ans : answers) {
            McqQuestions q = repository.findById(ans.getQuestionId()).orElse(null);
            if (q != null && q.getCorrectAnswer().equals(ans.getAnswer())) {
                score++;
            }
        }

        return score;
    }
}
