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
        existing.setCorrectAnswer(updatedRequest.getCorrectAnswer());

        return repository.save(existing);
    }

    public void deleteQuestion(Long id) {
        repository.deleteById(id);
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
