package com.interview.court.cases.controller;

import com.interview.court.cases.model.dto.DecisionDto;
import com.interview.court.cases.service.DecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Case Decision")
@RestController
@AllArgsConstructor
@RequestMapping(path = "decision")
@SecurityRequirement(name = "bearerAuth")
public class DecisionController {

    private final DecisionService decisionService;

    @Operation(
            description = "Endpoint for creating new Case decision",
            summary = "Create Case Decision"
    )
    @PostMapping(path = "/create")
    public ResponseEntity<?> createCourtCase(@RequestBody DecisionDto decisionDto,
                                             @RequestParam String caseLabel) {
        return decisionService.createNewDecision(decisionDto, caseLabel);
    }
}
//TODO implement other methods