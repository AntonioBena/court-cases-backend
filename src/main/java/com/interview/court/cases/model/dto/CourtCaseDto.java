package com.interview.court.cases.model.dto;

import com.interview.court.cases.model.court_case.CaseStatus;
import com.interview.court.cases.model.court.Court;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtCaseDto {
    private Long id;
    private String caseLabel;
    private CaseStatus caseStatus;
    private Court court;
    private List<DecisionDto> decisions;
    private String resolvingDecisionLabel;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourtCaseDto that = (CourtCaseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(caseLabel, that.caseLabel) && caseStatus == that.caseStatus && Objects.equals(court, that.court) && Objects.equals(decisions, that.decisions) && Objects.equals(resolvingDecisionLabel, that.resolvingDecisionLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caseLabel, caseStatus, court, decisions, resolvingDecisionLabel);
    }

    @Override
    public String toString() {
        return "CourtCaseDto{" +
                "id=" + id +
                ", caseLabel='" + caseLabel + '\'' +
                ", caseStatus=" + caseStatus +
                ", court=" + court +
                ", decisions=" + decisions +
                ", resolvingDecisionLabel='" + resolvingDecisionLabel + '\'' +
                '}';
    }
}