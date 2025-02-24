package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.court_case.Case;
import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.decision.Decision;
import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.model.dto.response.PageResponse;
import com.interview.court.cases.repository.CaseRepository;
import com.interview.court.cases.repository.CourtRepository;
import com.interview.court.cases.repository.DecisionRepository;
import com.interview.court.cases.service.CourtCaseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.interview.court.cases.model.court_case.CaseStatus.IN_PROGRESS;
import static com.interview.court.cases.model.court_case.CaseStatus.RESOLVED;

@Slf4j
@Service
@AllArgsConstructor
public class CourtCaseServiceImpl implements CourtCaseService {

    private final CaseRepository caseRepository;
    private final CourtRepository courtRepository;
    private final ModelMapper mapper;

    @Override
    public CourtCaseDto createCaseWithCourt(CaseAndCourtRequest caseAndCourtRequest) {
        var courtRequestDto = caseAndCourtRequest.getCourtDto();
        var caseRequestDto = caseAndCourtRequest.getCourtCaseDto();

        if (caseRepository.existsByCaseLabel(caseRequestDto.getCaseLabel())) {
            throw new IllegalStateException("Case already exists!");
        }

        var newCourt = courtRepository.findCourtByCourtName(courtRequestDto.getCourtName())
                .orElseGet(() -> courtRepository.save(mapper.map(courtRequestDto, Court.class)));

        var newCase = Case.builder()
                .caseLabel(caseRequestDto.getCaseLabel())
                .caseStatus(IN_PROGRESS)
                .court(newCourt)
                .description(caseRequestDto.getDescription())
                .build();

        return mapper.map(caseRepository.save(newCase), CourtCaseDto.class);
    }

    @Override
    @Transactional
    public CourtCaseDto updateCourtCase(CaseAndCourtRequest caseAndCourtRequest) {
        var courtRequestDto = caseAndCourtRequest.getCourtDto();
        var caseRequestDto = caseAndCourtRequest.getCourtCaseDto();

        log.info("input request: {}", caseAndCourtRequest);
        var caseToUpdate = caseRepository.findCaseByCaseLabel(caseRequestDto.getCaseLabel())
                .orElseThrow(()->new IllegalStateException("case for update doesnt exist")); //TODO exeptions

        if(!courtRepository.existsById(courtRequestDto.getId())) {
            throw new IllegalStateException("court not found");
        }
        var newCourt = courtRepository.save(mapper.map(courtRequestDto, Court.class));

        caseToUpdate.setCaseLabel(caseRequestDto.getCaseLabel());
        caseToUpdate.setCourt(newCourt);
        caseToUpdate.setDescription(caseRequestDto.getDescription());
        caseToUpdate.setDecisions(
                caseRequestDto.getDecisions()
                        .stream()
                        .map(d -> mapper.map(d, Decision.class))
                        .collect(Collectors.toSet())
        );

        var newDecisionLabel = caseRequestDto.getResolvingDecisionLabel();
        caseToUpdate.setResolvingDecisionLabel(newDecisionLabel);
        caseToUpdate.setCaseStatus(
                newDecisionLabel != null ? RESOLVED : IN_PROGRESS
        );
        caseToUpdate.setUpdatedAt(LocalDateTime.now());
        log.info("updated case: {}", caseToUpdate);

        var savedc = caseRepository.save(caseToUpdate);

        return mapper.map(savedc, CourtCaseDto.class);
    }

    @Override
    public PageResponse<CourtCaseDto> getAllDisplayableCases(int page, int size, boolean desc, String caseLabel,
                                        String courtName){
        Pageable pageable = PageRequest.of(page, size,
                desc ? Sort.by("createdAt").descending() : Sort.by("createdAt").ascending());
        Page<Case> cases = caseRepository.findAllCasesByParam(pageable, caseLabel, courtName);
        return mapCourtCaseToPageResponse(cases);
    }

    @Override
    public PageResponse<CourtCaseDto> getAllDisplayableCases(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Case> cases = caseRepository.findAllBy(pageable);
        return mapCourtCaseToPageResponse(cases);
    }


    private PageResponse<CourtCaseDto> mapCourtCaseToPageResponse(Page<Case> cases) {
        List<CourtCaseDto> casesDto = cases
                .stream()
                .map(c -> mapper.map(c, CourtCaseDto.class))
                .toList();
        log.info("cases number of total elements: {}, cases total elements - long {}",
                cases.getNumberOfElements(), cases.getTotalElements());
        return new PageResponse<>(
                casesDto,
                cases.getNumber(),
                cases.getSize(),
                cases.getTotalElements(),
                cases.getTotalPages(),
                cases.isLast(),
                cases.isFirst()
        );
    }
}