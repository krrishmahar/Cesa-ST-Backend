package com.cesaST.backend.repo;

import com.cesaST.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamNameAndLeaderName(
            String teamName,
            String leaderName
    );

    Team findByTeamName(String teamName);
}
