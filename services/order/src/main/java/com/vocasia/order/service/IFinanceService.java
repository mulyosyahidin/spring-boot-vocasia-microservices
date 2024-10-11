package com.vocasia.order.service;

import com.vocasia.order.dto.client.finance.InstructorIncomeDto;
import com.vocasia.order.dto.client.finance.PlatformIncomeDto;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;

public interface IFinanceService {

    InstructorIncomeDto saveInstructorIncome(NewInstructorIncomeRequest newInstructorIncomeRequest, String correlationId);
    PlatformIncomeDto savePlatformIncome(NewPlatformIncomeRequest newPlatformIncomeRequest, String correlationId);

}
