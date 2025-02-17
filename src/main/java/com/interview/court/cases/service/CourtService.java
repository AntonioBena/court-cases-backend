package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CourtRequest;

public interface CourtService {
    CourtDto createNewCourt(CourtRequest courtRequest);
    CourtDto updateCourt(CourtRequest courtRequest);
}