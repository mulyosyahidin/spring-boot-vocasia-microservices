package com.vocasia.finance.service;

import com.vocasia.finance.request.WithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWithdrawalRequestService {

    com.vocasia.finance.entity.WithdrawalRequest save(Long instructorId, WithdrawalRequest withdrawalRequest);
    com.vocasia.finance.entity.WithdrawalRequest update(com.vocasia.finance.entity.WithdrawalRequest withdrawalRequest);

    List<com.vocasia.finance.entity.WithdrawalRequest> findByInstructorId(Long instructorId);
    List<com.vocasia.finance.entity.WithdrawalRequest> findAllByStatus(String status);
    List<com.vocasia.finance.entity.WithdrawalRequest> findAll();

    int countByStatus(String status);
    Double sumAmountByStatus(String string);

    com.vocasia.finance.entity.WithdrawalRequest findById(Long id);

    Page<com.vocasia.finance.entity.WithdrawalRequest> findAllByInstructorId(Long instructorId, Pageable paging);

}
