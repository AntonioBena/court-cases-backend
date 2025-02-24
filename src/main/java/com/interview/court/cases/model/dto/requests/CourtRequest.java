package com.interview.court.cases.model.dto.requests;

import com.interview.court.cases.model.dto.CourtDto;
import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourtRequest {
    private CourtDto courtDto;

    @Override
    public String toString() {
        return "CourtRequest{" +
                "courtDto=" + courtDto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourtRequest that = (CourtRequest) o;
        return Objects.equals(courtDto, that.courtDto);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(courtDto);
    }
}