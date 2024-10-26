package com.vocasia.finance.service.impl;

import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.exception.ResourceNotFoundException;
import com.vocasia.finance.repository.InstructorBalanceHistoryRepository;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;
import com.vocasia.finance.service.IInstructorBalanceHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InstructorBalanceHistoryServiceImpl implements IInstructorBalanceHistoryService {

    private InstructorBalanceHistoryRepository instructorBalanceHistoryRepository;

    @Override
    public InstructorBalanceHistory save(InstructorBalance instructorBalance, NewInstructorBalanceHistoryRequest request) {
        InstructorBalanceHistory instructorBalanceHistory = new InstructorBalanceHistory();

        double newBalance;
        if (request.getType().equals("withdrawal")) {
            newBalance = instructorBalance.getCurrentBalance() - request.getAmount();
        } else {
            newBalance = instructorBalance.getCurrentBalance() + request.getAmount();
        }

        instructorBalanceHistory.setInstructorId(instructorBalance.getInstructorId());
        instructorBalanceHistory.setTransactionType(request.getType());
        instructorBalanceHistory.setAmount(request.getAmount());
        instructorBalanceHistory.setPreviousBalance(instructorBalance.getCurrentBalance());
        instructorBalanceHistory.setNewBalance(newBalance);
        instructorBalanceHistory.setTransactionDate(LocalDateTime.now());
        instructorBalanceHistory.setReferenceId(request.getReferenceId());
        instructorBalanceHistory.setReferenceType(request.getReferenceType());
        instructorBalanceHistory.setTransactionStatus(request.getTransactionStatus());
        instructorBalanceHistory.setRemarks(request.getRemarks());

        return instructorBalanceHistoryRepository.save(instructorBalanceHistory);
    }

    @Override
    public List<InstructorBalanceHistory> findByInstructorId(Long instructorId) {
        return instructorBalanceHistoryRepository.findByInstructorIdOrderByTransactionDateDescIdDesc(instructorId);
    }

    @Override
    public InstructorBalanceHistory findById(Long historyId) {
        return instructorBalanceHistoryRepository.findById(historyId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Page<InstructorBalanceHistory> findByInstructorId(Long instructorId, Pageable paging) {
        return instructorBalanceHistoryRepository.findByInstructorId(instructorId, paging);
    }

}
