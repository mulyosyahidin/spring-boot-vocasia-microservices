package com.vocasia.finance.service;

import com.vocasia.finance.entity.WithdrawalRequest;
import com.vocasia.finance.request.WithdrawalRequestRequest;
import com.vocasia.finance.types.WithdrawalRequestStatus;
import jakarta.validation.Valid;

import java.util.List;

public interface IWithdrawalRequestService {

    WithdrawalRequest save(Long instructorId, WithdrawalRequestRequest withdrawalRequest);
    WithdrawalRequest update(WithdrawalRequest withdrawalRequest);

    List<WithdrawalRequest> findByInstructorId(Long instructorId);
    List<WithdrawalRequest> findAllByStatus(String status);
    List<WithdrawalRequest> findAll();

    int countByStatus(String status);
    Double sumAmountByStatus(String string);

    WithdrawalRequest findById(Long id);

}
