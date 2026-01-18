package com.cesaST.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class McqResponse {
    private Long id;
    private String question;
    private List<String> options;
}

