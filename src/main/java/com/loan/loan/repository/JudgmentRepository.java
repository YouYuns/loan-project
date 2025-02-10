package com.loan.loan.repository;

import com.loan.loan.domain.Judgment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JudgmentRepository extends JpaRepository<Judgment, Long> {

    Optional<Judgment> findByApplicationId(Long ApplicationId);
}
