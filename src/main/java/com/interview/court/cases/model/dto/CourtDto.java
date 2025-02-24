package com.interview.court.cases.model.dto;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourtDto {
    private Long id;
    private String courtName;
    private String courtAddress;

    @Override
    public String toString() {
        return "CourtDto{" +
                "id=" + id +
                ", courtName='" + courtName + '\'' +
                ", courtAddress='" + courtAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CourtDto courtDto = (CourtDto) o;
        return Objects.equals(id, courtDto.id) && Objects.equals(courtName, courtDto.courtName) && Objects.equals(courtAddress, courtDto.courtAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courtName, courtAddress);
    }
}