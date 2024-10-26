package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PlatformBalanceHistoryDto {

    private Long id;

    @JsonProperty("transaction_type")
    private String transactionType;

    private Double amount;

    @JsonProperty("previous_balance")
    private Double previousBalance;

    @JsonProperty("new_balance")
    private Double newBalance;

    @JsonProperty("transaction_date")
    private LocalDateTime transactionDate;

    @JsonProperty("reference_id")
    private Long referenceId;

    @JsonProperty("reference_type")
    private String referenceType;

    @JsonProperty("transaction_status")
    private String transactionStatus;

    private String remarks;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

