package com.cesaST.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "teams")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String teamName;
    private String leaderName;

    @Column(columnDefinition = "integer default 0")
    private int round1Score;

    @Column(columnDefinition = "integer default 0")
    private int round2Score;

    @Column(columnDefinition = "integer default 0")
    private int round3Score;

    @Column(columnDefinition = "integer default 0")
    private int overallScore;

    private boolean active;

    private Instant createdAt;
}
