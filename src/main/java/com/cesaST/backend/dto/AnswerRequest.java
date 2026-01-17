package com.cesaST.backend.dto;

import lombok.Data;

@Data
public class AnswerRequest {
    private Long questionId;
    private String answer;
}
