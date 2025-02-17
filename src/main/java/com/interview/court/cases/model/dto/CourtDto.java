package com.interview.court.cases.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode //TODO ispraviti kasnije
public class CourtDto {
    private Long id;
    private String courtName;
    private String courtAddress;
}