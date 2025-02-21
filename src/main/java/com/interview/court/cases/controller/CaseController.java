package com.interview.court.cases.controller;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.service.CourtCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "case")
public class CaseController {

    private final CourtCaseService courtCaseService;

    @PostMapping(path = "/create")
    public ResponseEntity<CourtCaseDto> createCourtCase(@RequestBody CaseAndCourtRequest request) {
        return new ResponseEntity<>(courtCaseService.createCaseWithCourt(request), HttpStatus.OK); //TODO all
    }
}
