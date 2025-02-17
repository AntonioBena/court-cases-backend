package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseRequest;

public interface CourtCaseService {

    CourtCaseDto createCaseWithCourt(CaseRequest caseRequest);
    CourtCaseDto updateCourtCase(CaseRequest caseRequest);
}