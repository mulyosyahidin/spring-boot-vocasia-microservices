package com.vocasia.finance.service;

import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;

public interface IPlatformBalanceService {

    boolean isPlatformHasBalanceRecord();
    PlatformBalance findPlatformBalance();
    PlatformBalance save(PlatformBalance newPlatformBalance);
    PlatformBalance updateBalance(NewPlatformBalanceHistoryRequest request, PlatformBalance platformBalance, PlatformBalanceHistory platformBalanceHistory);

}
