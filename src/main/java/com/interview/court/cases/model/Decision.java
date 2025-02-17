package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "decision")
public class Decision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String decisionLabel; //TODO Presuda broj Kv-I-173/16, Odluka broj 29 Su-903/16

    @Column(name = "description", columnDefinition = "text")
    private String decisionDescription;

    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    private LocalDate decisionDate;
}