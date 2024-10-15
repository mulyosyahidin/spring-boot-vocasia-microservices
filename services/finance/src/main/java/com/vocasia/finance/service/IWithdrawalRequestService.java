package com.vocasia.finance.service;

import com.vocasia.finance.entity.WithdrawalRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IWithdrawalRequestService {

    WithdrawalRequest save(Long instructorId, com.vocasia.finance.request.WithdrawalRequest withdrawalRequest);
    WithdrawalRequest update(WithdrawalRequest withdrawalRequest);

    List<WithdrawalRequest> findByInstructorId(Long instructorId);
    List<WithdrawalRequest> findAllByStatus(String status);
    List<WithdrawalRequest> findAll();

    int countByStatus(String status);
    Double sumAmountByStatus(String string);

    WithdrawalRequest findById(Long id);

    Page<WithdrawalRequest> findAllByInstructorId(Long instructorId, Pageable paging);

    Page<WithdrawalRequest> findAllByStatus(String string, Pageable paging);
    Page<WithdrawalRequest> findAll(Pageable paging);
    
}
