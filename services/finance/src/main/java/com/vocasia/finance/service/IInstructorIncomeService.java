package com.vocasia.finance.service;

import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.request.NewInstructorIncomeRequest;

public interface IInstructorIncomeService {

    InstructorIncome save(NewInstructorIncomeRequest newInstructorIncomeRequest);

}
