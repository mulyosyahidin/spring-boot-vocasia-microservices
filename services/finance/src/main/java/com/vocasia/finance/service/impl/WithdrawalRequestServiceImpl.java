package com.vocasia.finance.service.impl;

import com.vocasia.finance.exception.ResourceNotFoundException;
import com.vocasia.finance.repository.WithdrawalRequestRepository;
import com.vocasia.finance.request.WithdrawalRequest;
import com.vocasia.finance.service.IWithdrawalRequestService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class WithdrawalRequestServiceImpl implements IWithdrawalRequestService {

    private WithdrawalRequestRepository withdrawalRequestRepository;

    @Override
    public com.vocasia.finance.entity.WithdrawalRequest save(Long instructorId, WithdrawalRequest withdrawalRequestRequest) {
        com.vocasia.finance.entity.WithdrawalRequest withdrawalRequest = new com.vocasia.finance.entity.WithdrawalRequest();

        withdrawalRequest.setInstructorId(instructorId);
        withdrawalRequest.setAmount(withdrawalRequestRequest.getAmount());
        withdrawalRequest.setStatus("PENDING");
        withdrawalRequest.setRequestedAt(LocalDateTime.now());
        withdrawalRequest.setBankName(withdrawalRequestRequest.getBankName());
        withdrawalRequest.setBankAccountName(withdrawalRequestRequest.getBankAccountName());
        withdrawalRequest.setBankAccountNumber(withdrawalRequestRequest.getBankAccountNumber());
        withdrawalRequest.setRemarks(withdrawalRequestRequest.getRemarks());

        return withdrawalRequestRepository.save(withdrawalRequest);
    }

    @Override
    public com.vocasia.finance.entity.WithdrawalRequest update(com.vocasia.finance.entity.WithdrawalRequest withdrawalRequest) {
        return withdrawalRequestRepository.save(withdrawalRequest);
    }

    @Override
    public List<com.vocasia.finance.entity.WithdrawalRequest> findByInstructorId(Long instructorId) {
        return withdrawalRequestRepository.findAllByInstructorId(instructorId);
    }

    @Override
    public List<com.vocasia.finance.entity.WithdrawalRequest> findAllByStatus(String status) {
        return withdrawalRequestRepository.findAllByStatus(status);
    }

    @Override
    public List<com.vocasia.finance.entity.WithdrawalRequest> findAll() {
        return withdrawalRequestRepository.findAll();
    }

    @Override
    public int countByStatus(String status) {
        return withdrawalRequestRepository.countByStatus(status);
    }

    @Override
    public Double sumAmountByStatus(String status) {
        Double totalAmount = withdrawalRequestRepository.sumAmountByStatus(status);

        return totalAmount != null ? totalAmount : 0.0;
    }

    @Override
    public com.vocasia.finance.entity.WithdrawalRequest findById(Long id) {
        return withdrawalRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Page<com.vocasia.finance.entity.WithdrawalRequest> findAllByInstructorId(Long instructorId, Pageable paging) {
        return withdrawalRequestRepository.findAllByInstructorId(instructorId, paging);
    }

}
