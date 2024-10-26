package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.PlatformBalanceDto;
import com.vocasia.finance.entity.PlatformBalance;

public class PlatformBalanceMapper {

    public static PlatformBalanceDto mapToDto(PlatformBalance entity) {
        PlatformBalanceDto dto = new PlatformBalanceDto();

        dto.setId(entity.getId());
        dto.setCurrentBalance(entity.getCurrentBalance());
        dto.setTotalIncome(entity.getTotalIncome());
        dto.setTotalPendingWithdrawal(entity.getTotalPendingWithdrawal());
        dto.setTotalWithdrawn(entity.getTotalWithdrawn());
        dto.setLastHistoryId(entity.getLastHistoryId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static PlatformBalance mapToEntity(PlatformBalanceDto dto) {
        PlatformBalance entity = new PlatformBalance();

        entity.setId(dto.getId());
        entity.setCurrentBalance(dto.getCurrentBalance());
        entity.setTotalIncome(dto.getTotalIncome());
        entity.setTotalPendingWithdrawal(dto.getTotalPendingWithdrawal());
        entity.setTotalWithdrawn(dto.getTotalWithdrawn());
        entity.setLastHistoryId(dto.getLastHistoryId());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }

}

