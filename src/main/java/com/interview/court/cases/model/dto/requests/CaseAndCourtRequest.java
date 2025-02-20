package com.interview.court.cases.model.dto.requests;


import com.interview.court.cases.model.dto.CourtCaseDto;
import com.interview.court.cases.model.dto.CourtDto;
import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CaseAndCourtRequest {
    private CourtCaseDto courtCaseDto;
    private CourtDto courtDto;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CaseAndCourtRequest that = (CaseAndCourtRequest) o;
        return Objects.equals(courtCaseDto, that.courtCaseDto) && Objects.equals(courtDto, that.courtDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courtCaseDto, courtDto);
    }

    @Override
    public String toString() {
        return "CaseAndCourtRequest{" +
                "courtCaseDto=" + courtCaseDto +
                ", courtDto=" + courtDto +
                '}';
    }
}