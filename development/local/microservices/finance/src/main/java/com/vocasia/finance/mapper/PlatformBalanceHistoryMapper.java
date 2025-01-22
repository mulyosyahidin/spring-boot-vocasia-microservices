package com.vocasia.finance.mapper;

import com.vocasia.finance.dto.data.PlatformBalanceHistoryDto;
import com.vocasia.finance.entity.PlatformBalanceHistory;

public class PlatformBalanceHistoryMapper {

    public static PlatformBalanceHistoryDto mapToDto(PlatformBalanceHistory entity) {
        PlatformBalanceHistoryDto dto = new PlatformBalanceHistoryDto();

        dto.setId(entity.getId());
        dto.setTransactionType(entity.getTransactionType());
        dto.setAmount(entity.getAmount());
        dto.setPreviousBalance(entity.getPreviousBalance());
        dto.setNewBalance(entity.getNewBalance());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setReferenceId(entity.getReferenceId());
        dto.setReferenceType(entity.getReferenceType());
        dto.setTransactionStatus(entity.getTransactionStatus());
        dto.setRemarks(entity.getRemarks());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public static PlatformBalanceHistory mapToEntity(PlatformBalanceHistoryDto dto) {
        PlatformBalanceHistory entity = new PlatformBalanceHistory();

        entity.setId(dto.getId());
        entity.setTransactionType(dto.getTransactionType());
        entity.setAmount(dto.getAmount());
        entity.setPreviousBalance(dto.getPreviousBalance());
        entity.setNewBalance(dto.getNewBalance());
        entity.setTransactionDate(dto.getTransactionDate());
        entity.setReferenceId(dto.getReferenceId());
        entity.setReferenceType(dto.getReferenceType());
        entity.setTransactionStatus(dto.getTransactionStatus());
        entity.setRemarks(dto.getRemarks());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }

}

