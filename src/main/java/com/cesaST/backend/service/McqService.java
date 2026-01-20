package com.cesaST.backend.service;

import com.cesaST.backend.controller.RoundOneController;
import com.cesaST.backend.dto.AnswerRequest;
import com.cesaST.backend.model.McqQuestions;
import com.cesaST.backend.repo.McqQuestionsRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class McqService {

    private final McqQuestionsRepository repository;
    private final RoundService roundService;

    public List<McqQuestions> getAllQuestions() {
        return repository.findAll();
    }

    public McqQuestions getQuestionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public McqQuestions createQuestion(McqQuestions question) {
        // Ensure ID is null so it creates a new entry
        question.setId(null);
        return repository.save(question);
    }

    public McqQuestions updateQuestion(Long id, McqQuestions updatedRequest) {

        McqQuestions existing = getQuestionById(id);

        existing.setQuestion(updatedRequest.getQuestion());
        existing.setOptions(updatedRequest.getOptions());
        existing.setCorrectAnswerIndex(updatedRequest.getCorrectAnswerIndex());

        return repository.save(existing);
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
    }


    public RoundOneController.Result submitRound1(String teamName, List<AnswerRequest> answers) {

        int score = evaluateAnswers(answers);

        roundService.validateAndSaveScore(
                teamName,
                1,
                score,
                answers.size()
        );

        return new RoundOneController.Result(score, answers.size());
    }

    // ================= EVALUATION =================

    public int evaluateAnswers(@NonNull List<AnswerRequest> answers) {
        int score = 0;

        for (AnswerRequest ans : answers) {
            McqQuestions q = repository.findById(ans.getQuestionId()).orElse(null);

            if (q != null && q.getCorrectAnswerIndex()
                    .equals(ans.getAnswerIndex())) {
                score++;
            }
        }

        return score;
    }


    public List<McqQuestions> getShuffledQuestions() {
        List<McqQuestions> list = repository.findAll();
        Collections.shuffle(list);
        return list;
    }
}
