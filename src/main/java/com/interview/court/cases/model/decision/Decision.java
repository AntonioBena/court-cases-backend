package com.interview.court.cases.model.decision;

import com.interview.court.cases.model.AuditingModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private LocalDateTime decisionDate;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Decision decision = (Decision) o;
        return Objects.equals(id, decision.id) && Objects.equals(decisionLabel, decision.decisionLabel) && Objects.equals(decisionDescription, decision.decisionDescription) && decisionType == decision.decisionType && Objects.equals(decisionDate, decision.decisionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, decisionLabel, decisionDescription, decisionType, decisionDate);
    }

    @Override
    public String toString() {
        return "Decision{" +
                "id=" + id +
                ", decisionLabel='" + decisionLabel + '\'' +
                ", decisionDescription='" + decisionDescription + '\'' +
                ", decisionType=" + decisionType +
                ", decisionDate=" + decisionDate +
                '}';
    }
}