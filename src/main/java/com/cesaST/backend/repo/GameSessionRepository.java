package com.cesaST.backend.repo;

import com.cesaST.backend.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
    Optional<GameSession> findByTeamId(String teamId);
}
