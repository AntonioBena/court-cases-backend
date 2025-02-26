package com.interview.court.cases.repository;

import com.interview.court.cases.model.court.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
    boolean existsByCourtName(String courtName);
    Optional<Court> findCourtByCourtName(String courtName);
}