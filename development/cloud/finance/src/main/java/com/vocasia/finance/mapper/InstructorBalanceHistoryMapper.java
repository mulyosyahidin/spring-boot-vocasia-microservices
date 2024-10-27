package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.InstructorBalanceHistoryDto;
import com.vocasia.finance.entity.InstructorBalanceHistory;

public class InstructorBalanceHistoryMapper {

    public static InstructorBalanceHistoryDto mapToDto(InstructorBalanceHistory history) {
        InstructorBalanceHistoryDto dto = new InstructorBalanceHistoryDto();

        dto.setId(history.getId());
        dto.setInstructorId(history.getInstructorId());
        dto.setTransactionType(history.getTransactionType());
        dto.setAmount(history.getAmount());
        dto.setPreviousBalance(history.getPreviousBalance());
        dto.setNewBalance(history.getNewBalance());
        dto.setTransactionDate(history.getTransactionDate());
        dto.setReferenceId(history.getReferenceId());
        dto.setReferenceType(history.getReferenceType());
        dto.setTransactionStatus(history.getTransactionStatus());
        dto.setRemarks(history.getRemarks());
        dto.setCreatedAt(history.getCreatedAt());
        dto.setUpdatedAt(history.getUpdatedAt());

        return dto;
    }

    public static InstructorBalanceHistory mapToEntity(InstructorBalanceHistoryDto dto) {
        InstructorBalanceHistory history = new InstructorBalanceHistory();

        history.setId(dto.getId());
        history.setInstructorId(dto.getInstructorId());
        history.setTransactionType(dto.getTransactionType());
        history.setAmount(dto.getAmount());
        history.setPreviousBalance(dto.getPreviousBalance());
        history.setNewBalance(dto.getNewBalance());
        history.setTransactionDate(dto.getTransactionDate());
        history.setReferenceId(dto.getReferenceId());
        history.setReferenceType(dto.getReferenceType());
        history.setTransactionStatus(dto.getTransactionStatus());
        history.setRemarks(dto.getRemarks());
        history.setCreatedAt(dto.getCreatedAt());
        history.setUpdatedAt(dto.getUpdatedAt());

        return history;
    }

}
