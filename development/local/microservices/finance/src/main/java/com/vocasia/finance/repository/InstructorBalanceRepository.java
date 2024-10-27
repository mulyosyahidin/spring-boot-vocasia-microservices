package com.vocasia.finance.repository;

import com.vocasia.finance.entity.InstructorBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface InstructorBalanceRepository extends JpaRepository<InstructorBalance, Long> {

    boolean existsByInstructorId(Long instructorId);

    InstructorBalance findByInstructorId(Long instructorId);

    @Query("SELECT SUM(ib.totalIncome) FROM InstructorBalance ib WHERE ib.instructorId = ?1 AND MONTH(ib.createdAt) = ?2 AND YEAR(ib.createdAt) = ?3")
    Double sumTotalIncomeByInstructorIdAndMonthAndYear(Long instructorId, int monthValue, int year);

}
