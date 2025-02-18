package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CourtRequest;
import org.springframework.http.ResponseEntity;

public interface CourtService {
    ResponseEntity<?> createNewCourt(CourtRequest courtRequest);
    CourtDto updateCourt(CourtRequest courtRequest);
}