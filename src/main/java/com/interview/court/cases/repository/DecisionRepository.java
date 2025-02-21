package com.interview.court.cases.repository;

import com.interview.court.cases.model.decision.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Long> {
    boolean existsDecisionByDecisionLabel(String decisionLabel);
}
