package com.vocasia.finance.service;

import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPlatformBalanceHistoryService {

    PlatformBalanceHistory save(PlatformBalance platformBalance, NewPlatformBalanceHistoryRequest request);
    List<PlatformBalanceHistory> findAll();

    Page<PlatformBalanceHistory> findAll(Pageable paging);

}
