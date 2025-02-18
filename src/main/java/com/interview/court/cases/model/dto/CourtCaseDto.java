package com.interview.court.cases.model.dto;

import com.interview.court.cases.model.court_case.CaseStatus;
import com.interview.court.cases.model.court.Court;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class CourtCaseDto {
    private Long id;
    private String caseLabel;
    private CaseStatus caseStatus;
    private Court court;
    private List<DecisionDto> decisions;
    private String resolvingDecisionLabel;
}