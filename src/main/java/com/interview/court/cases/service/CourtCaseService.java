package com.interview.court.cases.service;

import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.model.dto.response.PageResponse;

public interface CourtCaseService {

    CourtCaseDto createCaseWithCourt(CaseAndCourtRequest caseAndCourtRequest);
    CourtCaseDto updateCourtCase(CaseAndCourtRequest caseAndCourtRequest);
    PageResponse<CourtCaseDto> getAllDisplayableCases(int page, int size);
    PageResponse<CourtCaseDto> getAllDisplayableCases(int page, int size, boolean desc, String caseLabel,
                                                           String courtName);
}