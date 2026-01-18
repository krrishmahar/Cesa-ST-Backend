package com.cesaST.backend.service;

import com.cesaST.backend.dto.LoginResponse;
import com.cesaST.backend.dto.TeamLoginRequest;
import com.cesaST.backend.model.Team;
import com.cesaST.backend.repo.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


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
}
