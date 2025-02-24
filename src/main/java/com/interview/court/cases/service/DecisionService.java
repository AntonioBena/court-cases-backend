package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.DecisionDto;
import org.springframework.http.ResponseEntity;

public interface DecisionService {
    ResponseEntity<?> createNewDecision(DecisionDto decisionDto, String caseLabel);
}