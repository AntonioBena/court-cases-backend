package com.interview.court.cases.controller;


import com.interview.court.cases.model.dto.requests.CourtRequest;
import com.interview.court.cases.service.CourtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Court", description = "Not in use for now")
@RestController
@AllArgsConstructor
@RequestMapping(path = "court")
public class CourtController {

    private final CourtService courtService;

    @Operation(
            description = "Endpoint for creating new Court",
            summary = "Create new Court - not yet implemented in frontend"
    )
    @PostMapping(path = "/create")
    public ResponseEntity<?> createCourtCase(@RequestBody CourtRequest request) { //Not used for now
        return courtService.createNewCourt(request);
    }

    //TODO implement other methods
}