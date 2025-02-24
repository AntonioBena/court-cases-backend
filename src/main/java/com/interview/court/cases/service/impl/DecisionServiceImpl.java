package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.court_case.Case;
import com.interview.court.cases.model.decision.Decision;
import com.interview.court.cases.model.dto.DecisionDto;
import com.interview.court.cases.repository.CaseRepository;
import com.interview.court.cases.repository.DecisionRepository;
import com.interview.court.cases.service.DecisionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DecisionServiceImpl implements DecisionService {

    private final ModelMapper mapper;
    private final DecisionRepository decisionRepository;
    private final CaseRepository caseRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createNewDecision(DecisionDto decisionDto, String caseLabel) {
        log.info("Input decisionDto: " + decisionDto);
        var foundCase = caseRepository.findCaseByCaseLabel(caseLabel)
                .orElseThrow(()-> new IllegalStateException("doesnt exist"));

        return decisionRepository.existsDecisionByDecisionLabel(decisionDto.getDecisionLabel()) ?
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR) //TODO duplicatedecision error or something else
                .body(
                        Map.of("error",
                                "Decision with label: "+ decisionDto.getDecisionLabel() +
                                        " Already exist!."))
                : ResponseEntity.ok(addNewDecisionToCase(foundCase, decisionDto));
    }

    private DecisionDto addNewDecisionToCase(Case courtCase, DecisionDto decisionDto){
        var savedDecision = decisionRepository.save(mapper.map(decisionDto, Decision.class));
        courtCase.getDecisions().add(savedDecision);
        caseRepository.save(courtCase);
        log.debug("Decision added to court case" + decisionDto);
        return mapper.map(savedDecision, DecisionDto.class);
    }
}