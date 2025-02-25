package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.court_case.CaseStatus;
import com.interview.court.cases.model.decision.DecisionType;
import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.CourtDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.repository.CaseRepository;
import com.interview.court.cases.repository.CourtRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static com.interview.court.cases.TestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CourtCaseServiceTest {

    @Autowired
    private CaseRepository caseRepository;

    @Autowired
    private CourtRepository courtRepository;

    private ModelMapper modelMapper;

    private CourtCaseServiceImpl courtCaseService;

    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        courtCaseService = new CourtCaseServiceImpl(caseRepository, courtRepository, modelMapper);
    }

    @AfterEach
    void tearDown() {
        caseRepository.deleteAll();
    }

    @Test
    void test_should_update_case_and_mark_finished() {
        var court = generateNewCourt("court name", "court address");
        courtRepository.save(court);

        var decision = generateNewDecision("desc", "decision label", DecisionType.JUDGMENT);

        var newCase = generateNewCase("label1", CaseStatus.IN_PROGRESS, "desc",
                Set.of(decision), court);

        caseRepository.save(newCase);

        newCase.setResolvingDecisionLabel(decision.getDecisionLabel());

        var newUpdateRequest = CaseAndCourtRequest.builder()
                .courtCaseDto(modelMapper.map(newCase, CourtCaseDto.class))
                .courtDto(modelMapper.map(court, CourtDto.class))
                .build();

        courtCaseService.updateCourtCase(newUpdateRequest);

        var foundCase = caseRepository.findAll();

        assertEquals(CaseStatus.RESOLVED, foundCase.getFirst().getCaseStatus());
    }

    @Test
    void test_should_findAllCasesByParam_courtName() {
        var court = generateNewCourt("court name", "court address");
        courtRepository.save(court);

        for (int index = 0; index < 10; index++) {
            var newCase = generateNewCase("label" + index, CaseStatus.IN_PROGRESS,
                    "desc" + index, null, court);
            caseRepository.save(newCase);
        }

        var foundCases = courtCaseService.getAllDisplayableCases(0, 5, true, "",
                "court name");

        assertEquals(10, foundCases.getTotalElements());
        assertEquals(2, foundCases.getTotalPages());
    }

    @Test
    void test_should_findAllCasesByParam_caseLabel() {
        var court = generateNewCourt("court name", "court address");
        courtRepository.save(court);


        var newCase = generateNewCase("label", CaseStatus.IN_PROGRESS, "desc", null, court);
        caseRepository.save(newCase);


        var foundCases = courtCaseService.getAllDisplayableCases(0, 5, true,
                "label", "");

        assertEquals(1, foundCases.getTotalElements());
        assertEquals(1, foundCases.getTotalPages());
    }

    @Test
    void test_should_findAllCasesByParam_courtName_and_caseLabel() {
        var court = generateNewCourt("court name", "court address");
        courtRepository.save(court);

        for (int index = 0; index < 10; index++) {
            var newCase = generateNewCase("label" + index, CaseStatus.IN_PROGRESS,
                    "desc" + index, null, court);
            caseRepository.save(newCase);
        }

        var foundCases = courtCaseService.getAllDisplayableCases(0, 5, true,
                "label1", "court name");

        assertEquals(10, foundCases.getTotalElements());
        assertEquals(2, foundCases.getTotalPages());
    }
}