package com.interview.court.cases.service.impl;

import com.interview.court.cases.model.court_case.Case;
import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.requests.CaseAndCourtRequest;
import com.interview.court.cases.repository.CaseRepository;
import com.interview.court.cases.repository.CourtRepository;
import com.interview.court.cases.service.CourtCaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.interview.court.cases.model.court_case.CaseStatus.IN_PROGRESS;

@Slf4j
@Service
@AllArgsConstructor
public class CourtCaseServiceImpl implements CourtCaseService {

    private final CaseRepository caseRepository;
    private final CourtRepository courtRepository;
    private final ModelMapper mapper;

//TODO   Napraviti formu za unos podataka o novom predmetu. Unos podataka o novom predmetu
//       zahtijeva popunjavanje podatka o sudu i upisivanje oznake predmeta. (sud moze bit odabran iz liste)

    @Override
    public CourtCaseDto createCaseWithCourt(CaseAndCourtRequest caseAndCourtRequest) {
        var courtRequestDto = caseAndCourtRequest.getCourtDto();
        var caseRequestDto = caseAndCourtRequest.getCourtCaseDto();

        if (caseRepository.existsByCaseLabel(caseRequestDto.getCaseLabel())) {
            throw new IllegalStateException("Case already exists!");
        }
        var newCase = mapper.map(caseRequestDto, Case.class);

        var newCourt = courtRepository.findCourtByCourtName(courtRequestDto.getCourtName())
                .orElseGet(() -> courtRepository.save(mapper.map(courtRequestDto, Court.class)));

        newCase.setCourt(newCourt);
        newCase.setCaseStatus(IN_PROGRESS);
        return mapper.map(caseRepository.save(newCase), CourtCaseDto.class);
    }


//        var decisionsList = caseRequestDto.getDecisions().stream()
//                .filter(d ->
//                        !decisionRepository.existsDecisionByDecisionLabel(d.getDecisionLabel())
//                ).map(d -> decisionRepository.save(toDecisionEntity(d)))
//                .toList();

//        newCase.setDecisions(decisionsList);

    //TODO  Forma za ažuriranje podataka o predmetu mora imati i mogućnost upisivanja nove odluke na
//      predmet i mogućnost prebacivanja predmeta u status „riješen“ uz posebno označavanje odluke
//      kojom je predmet riješen.
    @Override
    public CourtCaseDto updateCourtCase(CaseAndCourtRequest caseAndCourtRequest) {
        var courtRequestDto = caseAndCourtRequest.getCourtDto();
        var caseRequestDto = caseAndCourtRequest.getCourtCaseDto();

        if (caseRepository.existsById(caseRequestDto.getId())) {
            throw new IllegalStateException("Case with id: " + caseRequestDto.getId() + "does not exist!");
        }
        //TODO make logic!
        return new CourtCaseDto();
    }
}