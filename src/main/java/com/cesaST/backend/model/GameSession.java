package com.cesaST.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "game_sessions")
@Getter
@Setter
public class GameSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamId;

    private Instant round1Start;
    private Instant round1End;

    private Integer round1Score;
}

