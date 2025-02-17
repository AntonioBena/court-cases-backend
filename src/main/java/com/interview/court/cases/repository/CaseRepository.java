package com.interview.court.cases.repository;

import com.interview.court.cases.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    boolean existsByCaseLabel(String caseLabel);
    Optional<Case> findCaseByCaseLabel(String caseLabel);
}
