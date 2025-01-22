package com.vocasia.finance.service.impl;

import com.vocasia.finance.entity.WithdrawalRequest;
import com.vocasia.finance.exception.ResourceNotFoundException;
import com.vocasia.finance.repository.WithdrawalRequestRepository;
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
    public WithdrawalRequest save(Long instructorId, com.vocasia.finance.request.WithdrawalRequest withdrawalRequestRequest) {
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();

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
    public WithdrawalRequest update(WithdrawalRequest withdrawalRequest) {
        return withdrawalRequestRepository.save(withdrawalRequest);
    }

    @Override
    public List<WithdrawalRequest> findByInstructorId(Long instructorId) {
        return withdrawalRequestRepository.findAllByInstructorId(instructorId);
    }

    @Override
    public List<WithdrawalRequest> findAllByStatus(String status) {
        return withdrawalRequestRepository.findAllByStatus(status);
    }

    @Override
    public List<WithdrawalRequest> findAll() {
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
    public WithdrawalRequest findById(Long id) {
        return withdrawalRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Page<WithdrawalRequest> findAllByInstructorId(Long instructorId, Pageable paging) {
        return withdrawalRequestRepository.findAllByInstructorId(instructorId, paging);
    }

    @Override
    public Page<WithdrawalRequest> findAllByStatus(String string, Pageable paging) {
        return withdrawalRequestRepository.findAllByStatus(string, paging);
    }

    @Override
    public Page<com.vocasia.finance.entity.WithdrawalRequest> findAll(Pageable paging) {
        return withdrawalRequestRepository.findAll(paging);
    }

}
