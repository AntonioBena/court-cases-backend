package com.interview.court.cases.model.dto.requests;

import com.interview.court.cases.model.dto.CourtDto;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class CourtRequest {
    private CourtDto courtDto;
}