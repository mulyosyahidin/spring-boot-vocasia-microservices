package com.vocasia.finance.service.impl;

import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.repository.InstructorBalanceRepository;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;
import com.vocasia.finance.service.IInstructorBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InstructorBalanceServiceImpl implements IInstructorBalanceService {

    private InstructorBalanceRepository instructorBalanceRepository;

    @Override
    public boolean isInstructorHasBalanceRecord(Long instructorId) {
        return instructorBalanceRepository.existsByInstructorId(instructorId);
    }

    @Override
    public InstructorBalance findByInstructorId(Long instructorId) {
        return instructorBalanceRepository.findByInstructorId(instructorId);
    }

    @Override
    public InstructorBalance save(InstructorBalance newInstructorBalance) {
        return instructorBalanceRepository.save(newInstructorBalance);
    }

    @Override
    public InstructorBalance updateBalance(NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest, InstructorBalance instructorBalance, InstructorBalanceHistory instructorBalanceHistory) {
        double currentBalance;
        double totalIncome;
        double totalPendingWithdrawal;
        double totalWithdrawn;
        double totalPlatformFee;

        if (newInstructorBalanceHistoryRequest.getType().equals("income")) {
            currentBalance = instructorBalance.getCurrentBalance() + newInstructorBalanceHistoryRequest.getAmount();
            totalIncome = instructorBalance.getTotalIncome() + newInstructorBalanceHistoryRequest.getAmount();
            totalPendingWithdrawal = instructorBalance.getTotalPendingWithdrawal();
            totalWithdrawn = instructorBalance.getTotalWithdrawn();
            totalPlatformFee = instructorBalance.getTotalPlatformFee() + newInstructorBalanceHistoryRequest.getPlatformFee();
        }
        else if (newInstructorBalanceHistoryRequest.getType().equals("withdrawal")) {
            currentBalance = instructorBalance.getCurrentBalance() - newInstructorBalanceHistoryRequest.getAmount();
            totalIncome = instructorBalance.getTotalIncome();
            totalPendingWithdrawal = instructorBalance.getTotalPendingWithdrawal() - newInstructorBalanceHistoryRequest.getAmount();
            totalWithdrawn = instructorBalance.getTotalWithdrawn() + newInstructorBalanceHistoryRequest.getAmount();
            totalPlatformFee = instructorBalance.getTotalPlatformFee();
        }
        else {
            currentBalance = instructorBalance.getCurrentBalance();
            totalIncome = instructorBalance.getTotalIncome();
            totalPendingWithdrawal = instructorBalance.getTotalPendingWithdrawal();
            totalWithdrawn = instructorBalance.getTotalWithdrawn();
            totalPlatformFee = instructorBalance.getTotalPlatformFee();
        }

        instructorBalance.setCurrentBalance(currentBalance);
        instructorBalance.setTotalIncome(totalIncome);
        instructorBalance.setTotalPendingWithdrawal(totalPendingWithdrawal);
        instructorBalance.setTotalWithdrawn(totalWithdrawn);
        instructorBalance.setTotalPlatformFee(totalPlatformFee);
        instructorBalance.setLastHistoryId(instructorBalanceHistory.getId());

        return instructorBalanceRepository.save(instructorBalance);
    }

    @Override
    public InstructorBalance updateTotalPendingWithdrawal(InstructorBalance instructorBalance, Double newCurrentPendingWithdrawal) {
        instructorBalance.setTotalPendingWithdrawal(newCurrentPendingWithdrawal);

        return instructorBalanceRepository.save(instructorBalance);
    }

    @Override
    public double sumTotalIncomeByInstructorIdAndMonthAndYear(Long instructorId, int monthValue, int year) {
        Double totalIncome = instructorBalanceRepository.sumTotalIncomeByInstructorIdAndMonthAndYear(instructorId, monthValue, year);

        return (totalIncome != null) ? totalIncome : 0.0;
    }

}
