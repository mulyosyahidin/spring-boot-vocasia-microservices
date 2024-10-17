package com.vocasia.finance.service;

import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface IInstructorBalanceService {

    boolean isInstructorHasBalanceRecord(Long instructorId);
    InstructorBalance findByInstructorId(Long instructorId);
    InstructorBalance save(InstructorBalance newInstructorBalance);
    InstructorBalance updateBalance(NewInstructorBalanceHistoryRequest request, InstructorBalance instructorBalance, InstructorBalanceHistory instructorBalanceHistory);
    InstructorBalance updateTotalPendingWithdrawal(InstructorBalance instructorBalance, Double newCurrentPendingWithdrawal);

    double sumTotalIncomeByInstructorIdAndMonthAndYear(Long instructorId, int monthValue, int year);
}
