package com.cesaST.backend.repo;

import com.cesaST.backend.model.McqQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface McqQuestionsRepository extends JpaRepository<McqQuestions, Long> {

    @Query(value = "SELECT * FROM mcq_questions ORDER BY random() LIMIT :limit", nativeQuery = true)
    List<McqQuestions> findRandom(@Param("limit") int limit);
}

