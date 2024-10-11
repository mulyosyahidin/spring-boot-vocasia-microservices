package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.InstructorIncomeDto;
import com.vocasia.finance.entity.InstructorIncome;

public class InstructorIncomeMapper {

    public static InstructorIncomeDto mapToDto(InstructorIncome instructorIncome) {
        InstructorIncomeDto instructorIncomeDto = new InstructorIncomeDto();

        Double getTotalPlatformFee = instructorIncome.getTotalUserPayment() * instructorIncome.getPlatformFeeInPercent().doubleValue() / 100;

        instructorIncomeDto.setId(instructorIncome.getId());
        instructorIncomeDto.setInstructorId(instructorIncome.getInstructorId());
        instructorIncomeDto.setOrderId(instructorIncome.getOrderId());
        instructorIncomeDto.setCourseId(instructorIncome.getCourseId());
        instructorIncomeDto.setTotalUserPayment(instructorIncome.getTotalUserPayment());
        instructorIncomeDto.setPlatformFeeInPercent(instructorIncome.getPlatformFeeInPercent());
        instructorIncomeDto.setTotalPlatformFee(getTotalPlatformFee);
        instructorIncomeDto.setTotalInstructorIncome(instructorIncome.getTotalInstructorIncome());
        instructorIncomeDto.setRemarks(instructorIncome.getRemarks());
        instructorIncomeDto.setCreatedAt(instructorIncome.getCreatedAt());
        instructorIncomeDto.setUpdatedAt(instructorIncome.getUpdatedAt());

        return instructorIncomeDto;
    }

    public static InstructorIncome mapToEntity(InstructorIncomeDto instructorIncomeDto) {
        InstructorIncome instructorIncome = new InstructorIncome();

        instructorIncome.setId(instructorIncomeDto.getId());
        instructorIncome.setInstructorId(instructorIncomeDto.getInstructorId());
        instructorIncome.setOrderId(instructorIncomeDto.getOrderId());
        instructorIncome.setCourseId(instructorIncomeDto.getCourseId());
        instructorIncome.setTotalUserPayment(instructorIncomeDto.getTotalUserPayment());
        instructorIncome.setPlatformFeeInPercent(instructorIncomeDto.getPlatformFeeInPercent());
        instructorIncome.setTotalInstructorIncome(instructorIncomeDto.getTotalInstructorIncome());
        instructorIncome.setRemarks(instructorIncomeDto.getRemarks());
        instructorIncome.setCreatedAt(instructorIncomeDto.getCreatedAt());
        instructorIncome.setUpdatedAt(instructorIncomeDto.getUpdatedAt());

        return instructorIncome;
    }

}
