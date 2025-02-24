package com.interview.court.cases.repository;

import com.interview.court.cases.model.court_case.Case;
import com.interview.court.cases.model.court_case.CaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    boolean existsByCaseLabel(String caseLabel);
    Optional<Case> findCaseByCaseLabel(String caseLabel);
    Page<Case> findAllBy(Pageable pageable);

    @Query("""
            SELECT c_case
            FROM Case c_case
            WHERE c_case.caseLabel = :caseLabel
            AND c_case.caseStatus = :caseStatus
            AND c_case.court.courtName = :courtName
            AND c_case.resolvingDecisionLabel = :resolvingDecisionLabel
""")
    Page<Case> findAllCasesByParam(Pageable pageable, String caseLabel,
                                       CaseStatus caseStatus, String courtName, String resolvingDecisionLabel);

    @Query("""
SELECT c_case
FROM Case c_case
WHERE c_case.caseLabel = :caseLabel
AND c_case.court.courtName = :courtName
""")
    Page<Case> findAllCasesByParam(Pageable pageable, String caseLabel, String courtName);
}