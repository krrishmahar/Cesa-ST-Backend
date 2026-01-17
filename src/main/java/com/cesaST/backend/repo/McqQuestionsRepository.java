package com.cesaST.backend.repo;

import com.cesaST.backend.model.McqQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface McqQuestionsRepository extends JpaRepository<McqQuestions, Long> {
}

