package com.vocasia.finance.service;

import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;

import java.util.List;

public interface IPlatformBalanceHistoryService {

    PlatformBalanceHistory save(PlatformBalance platformBalance, NewPlatformBalanceHistoryRequest request);
    List<PlatformBalanceHistory> findAll();

}
