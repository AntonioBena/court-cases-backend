package com.interview.court.cases.controller;

import com.interview.court.cases.model.dto.DecisionDto;
import com.interview.court.cases.service.DecisionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "decision")
public class DecisionController {

    private final DecisionService decisionService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createCourtCase(@RequestBody DecisionDto decisionDto,
                                             @RequestParam String caseLabel) {
        return decisionService.createNewDecision(decisionDto, caseLabel);
    }
}
