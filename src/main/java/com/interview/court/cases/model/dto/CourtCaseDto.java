package com.interview.court.cases.model.dto;

import com.interview.court.cases.model.CaseStatus;
import com.interview.court.cases.model.Court;
import com.interview.court.cases.model.dto.requests.DecisionDto;
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