package com.cesaST.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "round_scores",
        uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "round_no"})
)
@Getter
@Setter
public class RoundScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(columnDefinition = "integer default 1")
    private int roundNo;

    private int score;
    private int total;

    private LocalDateTime submittedAt = LocalDateTime.now();

}

