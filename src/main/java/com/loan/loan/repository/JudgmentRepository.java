package com.loan.loan.repository;

import com.loan.loan.domain.Judgment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JudgmentRepository extends JpaRepository<Judgment, Long> {
}
