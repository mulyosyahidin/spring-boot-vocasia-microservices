package com.vocasia.finance.service.impl;

import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.repository.PlatformBalanceHistoryRepository;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;
import com.vocasia.finance.service.IPlatformBalanceHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PlatformBalanceHistoryServiceImpl implements IPlatformBalanceHistoryService {

    private PlatformBalanceHistoryRepository platformBalanceHistoryRepository;

    @Override
    public PlatformBalanceHistory save(PlatformBalance platformBalance, NewPlatformBalanceHistoryRequest request) {
        PlatformBalanceHistory platformBalanceHistory = new PlatformBalanceHistory();

        platformBalanceHistory.setTransactionType(request.getType());
        platformBalanceHistory.setAmount(request.getAmount());
        platformBalanceHistory.setPreviousBalance(platformBalance.getCurrentBalance());
        platformBalanceHistory.setNewBalance(platformBalance.getCurrentBalance() + request.getAmount());
        platformBalanceHistory.setTransactionDate(LocalDateTime.now());
        platformBalanceHistory.setReferenceId(request.getReferenceId());
        platformBalanceHistory.setReferenceType(request.getReferenceType());
        platformBalanceHistory.setTransactionStatus(request.getTransactionStatus());
        platformBalanceHistory.setRemarks(request.getRemarks());

        return platformBalanceHistoryRepository.save(platformBalanceHistory);
    }

    @Override
    public List<PlatformBalanceHistory> findAll() {
        return platformBalanceHistoryRepository.findAll();
    }

    @Override
    public Page<PlatformBalanceHistory> findAll(Pageable paging) {
        return platformBalanceHistoryRepository.findAll(paging);
    }

}
