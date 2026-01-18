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

    private String teamName;
    private String leaderName;
//    private String teamCode;   // from Unstop / CollegeBuzz or whatever shaan is building

    private boolean active;

    private Instant createdAt;
}
