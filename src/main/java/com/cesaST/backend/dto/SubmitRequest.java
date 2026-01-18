package com.cesaST.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubmitRequest {
    private String userId;
    private List<AnswerRequest> answers;
}
