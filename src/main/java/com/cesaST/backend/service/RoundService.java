package com.cesaST.backend.service;

import com.cesaST.backend.model.RoundScore;
import com.cesaST.backend.model.Team;
import com.cesaST.backend.repo.RoundScoreRepository;
import com.cesaST.backend.repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final TeamRepository teamRepository;
    private final RoundScoreRepository roundScoreRepository;

    public void validateAndSaveScore(String teamName, int roundNo, int score, int total){
        Team team = teamRepository.findByTeamName(teamName).orElseThrow(() -> new RuntimeException("Team Not Found"));

        if (roundScoreRepository.existsByTeamAndRoundNo(team, roundNo)) {
            throw new RuntimeException("Round already submitted");
        }

        RoundScore rs = new RoundScore();
        rs.setTeam(team);
        rs.setRoundNo(roundNo);
        rs.setScore(score);
        rs.setTotal(total);

        roundScoreRepository.save(rs);

    }
}
