package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "court_case")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true, name = "case_label")
    private String caseLabel;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CaseStatus caseStatus;

    @ManyToOne(optional = false)
    @JoinColumn(name = "court_id")
    private Court court;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //TODO test
    @JoinColumn(name = "case_id")
    private List<Decision> decisions;

    ///TODO resolvind dedecision should be in decisions, Kv-I-173/16
    @Column(name = "resolved_with_decision")
    private String resolvingDecisionLabel;
}