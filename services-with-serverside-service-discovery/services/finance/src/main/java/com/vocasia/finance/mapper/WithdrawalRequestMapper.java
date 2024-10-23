package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.WithdrawalRequestDto;
import com.vocasia.finance.entity.WithdrawalRequest;

public class WithdrawalRequestMapper {

    public static WithdrawalRequestDto mapToDto(WithdrawalRequest withdrawalRequest) {
        WithdrawalRequestDto dto = new WithdrawalRequestDto();

        dto.setId(withdrawalRequest.getId());
        dto.setInstructorId(withdrawalRequest.getInstructorId());
        dto.setAmount(withdrawalRequest.getAmount());
        dto.setStatus(withdrawalRequest.getStatus());
        dto.setRequestedAt(withdrawalRequest.getRequestedAt());
        dto.setProcessedAt(withdrawalRequest.getProcessedAt());
        dto.setBankName(withdrawalRequest.getBankName());
        dto.setBankAccountName(withdrawalRequest.getBankAccountName());
        dto.setBankAccountNumber(withdrawalRequest.getBankAccountNumber());
        dto.setRemarks(withdrawalRequest.getRemarks());
        dto.setCreatedAt(withdrawalRequest.getCreatedAt());
        dto.setUpdatedAt(withdrawalRequest.getUpdatedAt());

        return dto;
    }

    public static WithdrawalRequest mapToEntity(WithdrawalRequestDto dto) {
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();

        withdrawalRequest.setId(dto.getId());
        withdrawalRequest.setInstructorId(dto.getInstructorId());
        withdrawalRequest.setAmount(dto.getAmount());
        withdrawalRequest.setStatus(dto.getStatus());
        withdrawalRequest.setRequestedAt(dto.getRequestedAt());
        withdrawalRequest.setProcessedAt(dto.getProcessedAt());
        withdrawalRequest.setBankName(dto.getBankName());
        withdrawalRequest.setBankAccountName(dto.getBankAccountName());
        withdrawalRequest.setBankAccountNumber(dto.getBankAccountNumber());
        withdrawalRequest.setRemarks(dto.getRemarks());
        withdrawalRequest.setCreatedAt(dto.getCreatedAt());
        withdrawalRequest.setUpdatedAt(dto.getUpdatedAt());

        return withdrawalRequest;
    }

}
