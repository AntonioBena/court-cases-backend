package com.interview.court.cases.model.decision;

import com.interview.court.cases.model.AuditingModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
 //TODO make optimization
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "decision")
public class Decision extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String decisionLabel; //TODO Presuda broj Kv-I-173/16, Odluka broj 29 Su-903/16

    @Column(name = "description", columnDefinition = "text")
    private String decisionDescription;

    @Enumerated(EnumType.STRING)
    private DecisionType decisionType;

    private LocalDate decisionDate;
}