package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;

public interface CourtCaseService {

    CourtCaseDto createCaseWithCourt(CaseAndCourtRequest caseAndCourtRequest);
    CourtCaseDto updateCourtCase(CaseAndCourtRequest caseAndCourtRequest);
}