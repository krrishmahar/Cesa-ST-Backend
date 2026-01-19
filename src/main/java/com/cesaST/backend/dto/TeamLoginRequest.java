package com.cesaST.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamLoginRequest {
    private String teamName;
    private String leaderName;
}

