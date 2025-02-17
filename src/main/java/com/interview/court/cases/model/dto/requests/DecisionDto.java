package com.interview.court.cases.model.dto.requests;

import com.interview.court.cases.model.DecisionType;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class DecisionDto {
    private Long id;
    private String decisionLabel; //TODO Presuda broj Kv-I-173/16, Odluka broj 29 Su-903/16
    private String decisionDescription;
    private DecisionType decisionType;
    private LocalDate decisionDate;
}