package com.vocasia.finance.service;

import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.request.NewInstructorIncomeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInstructorIncomeService {

    InstructorIncome save(NewInstructorIncomeRequest newInstructorIncomeRequest);
    InstructorIncome findById(Long incomeId);

    Page<InstructorIncome> findAllByInstructorId(Long instructorId, Pageable paging);
    Page<InstructorIncome> findAllByCourseId(Long courseId, Pageable paging);
    List<InstructorIncome> findAllByCourseId(Long courseId);

}
