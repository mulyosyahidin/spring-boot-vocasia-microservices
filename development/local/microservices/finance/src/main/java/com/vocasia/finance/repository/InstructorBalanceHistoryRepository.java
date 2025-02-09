package com.vocasia.finance.repository;

import com.vocasia.finance.entity.InstructorBalanceHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorBalanceHistoryRepository extends JpaRepository<InstructorBalanceHistory, Long> {

    List<InstructorBalanceHistory> findByInstructorId(Long instructorId);
    List<InstructorBalanceHistory> findByInstructorIdOrderByTransactionDateDesc(Long instructorId);
    List<InstructorBalanceHistory> findByInstructorIdOrderByTransactionDateDescIdDesc(Long instructorId);

    Page<InstructorBalanceHistory> findByInstructorId(Long instructorId, Pageable paging);

}
