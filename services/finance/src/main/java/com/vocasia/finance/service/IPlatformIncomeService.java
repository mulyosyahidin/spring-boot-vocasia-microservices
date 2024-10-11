package com.vocasia.finance.service;

import com.vocasia.finance.entity.PlatformIncome;
import com.vocasia.finance.request.NewPlatformIncomeRequest;

public interface IPlatformIncomeService {

    PlatformIncome save(NewPlatformIncomeRequest newPlatformIncomeRequest);
}
