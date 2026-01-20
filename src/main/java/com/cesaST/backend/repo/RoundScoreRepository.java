package com.cesaST.backend.repo;

import com.cesaST.backend.model.RoundScore;
import com.cesaST.backend.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoundScoreRepository extends JpaRepository<RoundScore, Long> {

    boolean existsByTeamAndRoundNo(Team team, int roundNo);

    Optional<RoundScore> findByTeamAndRoundNo(Team team, int roundNo);

    @Query("""
      SELECT rs.team.teamName, SUM(rs.score)
      FROM RoundScore rs
      GROUP BY rs.team.teamName
      ORDER BY SUM(rs.score) DESC
    """)
    List<Object[]> globalLeaderboard();

    List<RoundScore> findByRoundNoOrderByScoreDesc(int roundNo);
}

