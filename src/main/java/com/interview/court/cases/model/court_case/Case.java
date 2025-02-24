package com.interview.court.cases.model.court_case;

import com.interview.court.cases.model.AuditingModel;
import com.interview.court.cases.model.court.Court;
import com.interview.court.cases.model.decision.Decision;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "court_case")
public class Case extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(unique=true, name = "case_label")
    private String caseLabel;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "court_id")
    private Court court;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "case_id")
    private Set<Decision> decisions;

    ///TODO resolvind dedecision should be in decisions, Kv-I-173/16
    @Column(name = "resolved_with_decision")
    private String resolvingDecisionLabel;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(id, aCase.id) && Objects.equals(caseLabel, aCase.caseLabel) && caseStatus == aCase.caseStatus && Objects.equals(description, aCase.description) && Objects.equals(court, aCase.court) && Objects.equals(decisions, aCase.decisions) && Objects.equals(resolvingDecisionLabel, aCase.resolvingDecisionLabel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, caseLabel, caseStatus, description, court, decisions, resolvingDecisionLabel);
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", caseLabel='" + caseLabel + '\'' +
                ", caseStatus=" + caseStatus +
                ", description='" + description + '\'' +
                ", court=" + court +
                ", decisions=" + decisions +
                ", resolvingDecisionLabel='" + resolvingDecisionLabel + '\'' +
                '}';
    }
}