package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.PlatformIncomeDto;
import com.vocasia.finance.entity.PlatformIncome;

public class PlatformIncomeMapper {

    public static PlatformIncomeDto mapToDto(PlatformIncome platformIncome) {
        PlatformIncomeDto platformIncomeDto = new PlatformIncomeDto();

        platformIncomeDto.setId(platformIncome.getId());
        platformIncomeDto.setInstructorId(platformIncome.getInstructorId());
        platformIncomeDto.setOrderId(platformIncome.getOrderId());
        platformIncomeDto.setCourseId(platformIncome.getCourseId());
        platformIncomeDto.setTotalUserPayment(platformIncome.getTotalUserPayment());
        platformIncomeDto.setPlatformFeeInPercent(platformIncome.getPlatformFeeInPercent());
        platformIncomeDto.setTotalPlatformIncome(platformIncome.getTotalPlatformIncome());
        platformIncomeDto.setRemarks(platformIncome.getRemarks());
        platformIncomeDto.setCreatedAt(platformIncome.getCreatedAt());
        platformIncomeDto.setUpdatedAt(platformIncome.getUpdatedAt());

        return platformIncomeDto;
    }

    public static PlatformIncome mapToEntity(PlatformIncomeDto platformIncomeDto) {
        PlatformIncome platformIncome = new PlatformIncome();

        platformIncome.setId(platformIncomeDto.getId());
        platformIncome.setInstructorId(platformIncomeDto.getInstructorId());
        platformIncome.setOrderId(platformIncomeDto.getOrderId());
        platformIncome.setCourseId(platformIncomeDto.getCourseId());
        platformIncome.setTotalUserPayment(platformIncomeDto.getTotalUserPayment());
        platformIncome.setPlatformFeeInPercent(platformIncomeDto.getPlatformFeeInPercent());
        platformIncome.setTotalPlatformIncome(platformIncomeDto.getTotalPlatformIncome());
        platformIncome.setRemarks(platformIncomeDto.getRemarks());
        platformIncome.setCreatedAt(platformIncomeDto.getCreatedAt());
        platformIncome.setUpdatedAt(platformIncomeDto.getUpdatedAt());

        return platformIncome;
    }
}
