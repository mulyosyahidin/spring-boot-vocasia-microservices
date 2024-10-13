package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.InstructorBalanceDto;
import com.vocasia.finance.entity.InstructorBalance;

public class InstructorBalanceMapper {

    public static InstructorBalanceDto mapToDto(InstructorBalance instructorBalance) {
        InstructorBalanceDto dto = new InstructorBalanceDto();

        dto.setId(instructorBalance.getId());
        dto.setInstructorId(instructorBalance.getInstructorId());
        dto.setCurrentBalance(instructorBalance.getCurrentBalance());
        dto.setTotalIncome(instructorBalance.getTotalIncome());
        dto.setTotalPendingWithdrawal(instructorBalance.getTotalPendingWithdrawal());
        dto.setTotalWithdrawn(instructorBalance.getTotalWithdrawn());
        dto.setTotalPlatformFee(instructorBalance.getTotalPlatformFee());
        dto.setLastHistoryId(instructorBalance.getLastHistoryId());
        dto.setCreatedAt(instructorBalance.getCreatedAt());
        dto.setUpdatedAt(instructorBalance.getUpdatedAt());

        return dto;
    }

    public static InstructorBalance mapToEntity(InstructorBalanceDto dto) {
        InstructorBalance instructorBalance = new InstructorBalance();

        instructorBalance.setId(dto.getId());
        instructorBalance.setInstructorId(dto.getInstructorId());
        instructorBalance.setCurrentBalance(dto.getCurrentBalance());
        instructorBalance.setTotalIncome(dto.getTotalIncome());
        instructorBalance.setTotalPendingWithdrawal(dto.getTotalPendingWithdrawal());
        instructorBalance.setTotalWithdrawn(dto.getTotalWithdrawn());
        instructorBalance.setTotalPlatformFee(dto.getTotalPlatformFee());
        instructorBalance.setLastHistoryId(dto.getLastHistoryId());
        instructorBalance.setCreatedAt(dto.getCreatedAt());
        instructorBalance.setUpdatedAt(dto.getUpdatedAt());

        return instructorBalance;
    }

}

