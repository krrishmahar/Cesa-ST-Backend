package com.cesaST.backend.controller;

import com.cesaST.backend.dto.LoginResponse;
import com.cesaST.backend.dto.TeamLoginRequest;
import com.cesaST.backend.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TeamService teamService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody TeamLoginRequest req) {
        return ResponseEntity.ok(teamService.login(req));
    }
}

