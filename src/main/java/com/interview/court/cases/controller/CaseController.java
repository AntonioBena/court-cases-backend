package com.interview.court.cases.controller;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.model.dto.response.PageResponse;
import com.interview.court.cases.service.CourtCaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Court Case")
@RestController
@AllArgsConstructor
@RequestMapping(path = "case")
@SecurityRequirement(name = "bearerAuth")
public class CaseController {

    private final CourtCaseService courtCaseService;

    @Operation(
            description = "Endpoint for creating new Court Cases",
            summary = "Create new Court Case with the corresponding Court"
    )
    @PostMapping(path = "/create")
    public ResponseEntity<?> createCourtCase(@RequestBody CaseAndCourtRequest request) {
        return new ResponseEntity<>(courtCaseService.createCaseWithCourt(request), HttpStatus.OK); //TODO all
    }

    @Operation(
            description = "Endpoint for updating Court Case",
            summary = "Update Court Case with the corresponding Court"
    )
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateCourtCase(@RequestBody CaseAndCourtRequest request) {
        return new ResponseEntity<>(courtCaseService.updateCourtCase(request), HttpStatus.OK);
    }

    @Operation(
            description = "Endpoint for getting  Court Cases",
            summary = "Get Court Case with the corresponding Court - with pagination"
    )
    @GetMapping
    public ResponseEntity<PageResponse<CourtCaseDto>> getCases(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(courtCaseService.getAllDisplayableCases(page, size));
    }

    @Operation(
            description = "Endpoint for getting  Court Cases",
            summary = "Get Court Case with the corresponding Court - with pagination and filters"
    )
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