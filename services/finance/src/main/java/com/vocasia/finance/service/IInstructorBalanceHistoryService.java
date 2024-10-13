package com.vocasia.finance.service;

import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;

import java.util.List;

public interface IInstructorBalanceHistoryService {

    InstructorBalanceHistory save(InstructorBalance instructorBalance, NewInstructorBalanceHistoryRequest request);
    List<InstructorBalanceHistory> findByInstructorId(Long instructorId);
    InstructorBalanceHistory findById(Long historyId);

}
