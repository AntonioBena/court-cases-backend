package com.interview.court.cases.controller;


import com.interview.court.cases.model.dto.requests.CourtRequest;
import com.interview.court.cases.service.CourtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/court")
public class CourtController {

    private final CourtService courtService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createCourtCase(@RequestBody CourtRequest request) {
        return courtService.createNewCourt(request);
    }
}
