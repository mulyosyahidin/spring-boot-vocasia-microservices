package com.vocasia.finance.repository;

import com.vocasia.finance.entity.InstructorBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorBalanceRepository extends JpaRepository<InstructorBalance, Long> {

    boolean existsByInstructorId(Long instructorId);
    InstructorBalance findByInstructorId(Long instructorId);

}
