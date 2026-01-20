package com.cesaST.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mcq_questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class McqQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @ElementCollection
    @CollectionTable(name = "mcq_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_text")
    private List<String> options;

    @JsonIgnore
    private Integer correctAnswerIndex;
}
