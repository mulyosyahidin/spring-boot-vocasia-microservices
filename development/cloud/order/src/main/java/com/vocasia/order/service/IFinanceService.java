package com.vocasia.order.service;

import com.vocasia.order.dto.client.finance.*;
import com.vocasia.order.request.client.finance.NewInstructorBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;

import java.util.List;

public interface IFinanceService {

    InstructorIncomeDto saveInstructorIncome(NewInstructorIncomeRequest newInstructorIncomeRequest, String correlationId);
    PlatformIncomeDto savePlatformIncome(NewPlatformIncomeRequest newPlatformIncomeRequest, String correlationId);
    InstructorBalanceDto saveInstructorBalance(NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest, String correlationId);
    PlatformBalanceDto savePlatformBalance(NewPlatformBalanceHistoryRequest newPlatformBalanceHistoryRequest, String correlationId);

    List<InstructorIncomeDetailDto> findInstructorIncomesByOrderId(Long orderId, String correlationId);
    List<PlatformIncomeDetailDto> findPlatformIncomesByOrderId(Long orderId, String correlationId);

}
