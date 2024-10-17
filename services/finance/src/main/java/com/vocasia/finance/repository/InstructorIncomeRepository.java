package com.vocasia.finance.repository;

import com.vocasia.finance.entity.InstructorIncome;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorIncomeRepository extends JpaRepository<InstructorIncome, Long> {

    Page<InstructorIncome> findAllByInstructorId(Long instructorId, Pageable paging);
    Page<InstructorIncome> findAllByCourseId(Long courseId, Pageable paging);
    List<InstructorIncome> findAllByCourseId(Long courseId);

    List<InstructorIncome> findAllByOrderId(Long orderId);
    List<InstructorIncome> findAllByInstructorId(Long instructorId);

}
