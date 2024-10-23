package com.vocasia.finance.service;

import com.vocasia.finance.entity.PlatformIncome;
import com.vocasia.finance.request.NewPlatformIncomeRequest;

import java.util.List;

public interface IPlatformIncomeService {

    PlatformIncome save(NewPlatformIncomeRequest newPlatformIncomeRequest);
    List<PlatformIncome> findAllByOrderId(Long orderId);

}
