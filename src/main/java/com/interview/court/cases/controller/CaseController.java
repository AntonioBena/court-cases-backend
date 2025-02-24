package com.interview.court.cases.controller;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.model.dto.response.PageResponse;
import com.interview.court.cases.service.CourtCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "case")
public class CaseController {

    private final CourtCaseService courtCaseService;

    @PostMapping(path = "/create")
    public ResponseEntity<CourtCaseDto> createCourtCase(@RequestBody CaseAndCourtRequest request) {
        return new ResponseEntity<>(courtCaseService.createCaseWithCourt(request), HttpStatus.OK); //TODO all
    }

    @PostMapping(path = "/update")
    public ResponseEntity<CourtCaseDto> updateCourtCase(@RequestBody CaseAndCourtRequest request) {
        return new ResponseEntity<>(courtCaseService.updateCourtCase(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageResponse<CourtCaseDto>> getCases(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(courtCaseService.getAllDisplayableCases(page, size));
    }

    @GetMapping(path = "/by-filter")
    public ResponseEntity<PageResponse<CourtCaseDto>> getCasesByFilter(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "5", required = false) int size,
            @RequestParam(name = "isDesc", defaultValue = "true") boolean isDesc,
            @RequestParam(name = "caseLabel", defaultValue = "") String caseLabel,
            @RequestParam(name = "courtName", defaultValue = "") String courtName
    ) {
        return ResponseEntity.ok(courtCaseService.getAllDisplayableCases(page, size, isDesc, caseLabel, courtName));
    }
}