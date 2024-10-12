package com.vocasia.finance.service;

import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.request.NewInstructorIncomeRequest;

import java.util.List;

public interface IInstructorIncomeService {

    InstructorIncome save(NewInstructorIncomeRequest newInstructorIncomeRequest);
    List<InstructorIncome> findAllIncomeByCourseId(Long courseId);
    List<InstructorIncome> getAllInstructorIncome(Long instructorId);
    InstructorIncome findById(Long incomeId);

}
