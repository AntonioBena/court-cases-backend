package com.interview.court.cases.model.dto;

import com.interview.court.cases.model.decision.DecisionType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DecisionDto {
    private Long id;
    private String decisionLabel; //TODO Presuda broj Kv-I-173/16, Odluka broj 29 Su-903/16
    private String decisionDescription;
    private DecisionType decisionType;
    private LocalDateTime decisionDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DecisionDto that = (DecisionDto) o;
        return Objects.equals(id, that.id) && Objects.equals(decisionLabel, that.decisionLabel) && Objects.equals(decisionDescription, that.decisionDescription) && decisionType == that.decisionType && Objects.equals(decisionDate, that.decisionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, decisionLabel, decisionDescription, decisionType, decisionDate);
    }

    @Override
    public String toString() {
        return "DecisionDto{" +
                "id=" + id +
                ", decisionLabel='" + decisionLabel + '\'' +
                ", decisionDescription='" + decisionDescription + '\'' +
                ", decisionType=" + decisionType +
                ", decisionDate=" + decisionDate +
                '}';
    }
}