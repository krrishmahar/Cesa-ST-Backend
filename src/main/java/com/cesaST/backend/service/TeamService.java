package com.cesaST.backend.service;

import com.cesaST.backend.dto.LoginResponse;
import com.cesaST.backend.dto.TeamLoginRequest;
import com.cesaST.backend.model.Team;
import com.cesaST.backend.repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepo;
    private final JwtService jwtService;

    public LoginResponse login(TeamLoginRequest req) {

        Team team = teamRepo
                .findByTeamNameAndLeaderName(
                        req.getTeamName(),
                        req.getLeaderName()
                )
                .orElseThrow(() -> new RuntimeException("Invalid Team"));

        String token = jwtService.generateToken(team.getTeamName());

        return new LoginResponse(token);
    }

    public void updateRound1Score(String teamName, int newScore) {
        try {
            Team team = teamRepo.findByTeamName(teamName).orElseThrow(() -> new RuntimeException("Invalid Team"));
            // HIGHEST SCORE LOGIC: Only update if the new score is better
            if (newScore > team.getRound1Score()) {
                team.setRound1Score(newScore);
                teamRepo.save(team);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateHighestScore(String teamName, int round, int newScore) {
        Team team = teamRepo.findByTeamName(teamName)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        boolean isUpdated = false;

        // HIGHEST SCORE LOGIC: Only update if newScore > currentScore
        if (round == 1 && newScore > team.getRound1Score()) {
            team.setRound1Score(newScore);
            isUpdated = true;
        } else if (round == 2 && newScore > team.getRound2Score()) {
            team.setRound2Score(newScore);
            isUpdated = true;
        } else if (round == 3 && newScore > team.getRound3Score()) {
            team.setRound3Score(newScore);
            isUpdated = true;
        }

        // Only recalculate overall score and save if a round score improved
        if (isUpdated) {
            int total = team.getRound1Score() + team.getRound2Score() + team.getRound3Score();
            team.setOverallScore(total);
            teamRepo.save(team);
        }
    }

    public List<Team> getLeaderboard() {
        return teamRepo.findAll(Sort.by(Sort.Direction.DESC, "overallScore"));
    }

}
