package com.vocasia.finance.repository;

import com.vocasia.finance.entity.InstructorIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorIncomeRepository extends JpaRepository<InstructorIncome, Long> {

    List<InstructorIncome> findAllByCourseId(Long courseId);
    List<InstructorIncome> findAllByInstructorId(Long instructorId);

}
