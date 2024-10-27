package com.vocasia.finance.service.impl;

import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.repository.PlatformBalanceRepository;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;
import com.vocasia.finance.service.IPlatformBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformBalanceServiceImpl implements IPlatformBalanceService {

    private PlatformBalanceRepository platformBalanceRepository;

    @Override
    public boolean isPlatformHasBalanceRecord() {
        return platformBalanceRepository.count() > 0;
    }

    @Override
    public PlatformBalance findPlatformBalance() {
        List<PlatformBalance> balances = platformBalanceRepository.findAll();
        if (balances.isEmpty()) {
            return null;
        }

        return balances.get(0);
    }

    @Override
    public PlatformBalance save(PlatformBalance newPlatformBalance) {
        return platformBalanceRepository.save(newPlatformBalance);
    }

    @Override
    public PlatformBalance updateBalance(NewPlatformBalanceHistoryRequest request, PlatformBalance platformBalance, PlatformBalanceHistory platformBalanceHistory) {
        double currentBalance;
        double totalIncome;
        double totalPendingWithdrawal;
        double totalWithdrawn;

        if (request.getType().equals("fee")) {
            currentBalance = platformBalance.getCurrentBalance() + request.getAmount();
            totalIncome = platformBalance.getTotalIncome() + request.getAmount();
            totalPendingWithdrawal = platformBalance.getTotalPendingWithdrawal();
            totalWithdrawn = platformBalance.getTotalWithdrawn();
        }
        else {
            currentBalance = platformBalance.getCurrentBalance();
            totalIncome = platformBalance.getTotalIncome();
            totalPendingWithdrawal = platformBalance.getTotalPendingWithdrawal();
            totalWithdrawn = platformBalance.getTotalWithdrawn();
        }

        platformBalance.setCurrentBalance(currentBalance);
        platformBalance.setTotalIncome(totalIncome);
        platformBalance.setTotalPendingWithdrawal(totalPendingWithdrawal);
        platformBalance.setTotalWithdrawn(totalWithdrawn);
        platformBalance.setLastHistoryId(platformBalanceHistory.getId());

        return platformBalanceRepository.save(platformBalance);
    }

}
