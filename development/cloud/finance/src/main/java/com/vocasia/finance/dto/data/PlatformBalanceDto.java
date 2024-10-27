package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PlatformBalanceDto {

    private Long id;

    @JsonProperty("current_balance")
    private Double currentBalance;

    @JsonProperty("total_income")
    private Double totalIncome;

    @JsonProperty("total_pending_withdrawal")
    private Double totalPendingWithdrawal;

    @JsonProperty("total_withdrawn")
    private Double totalWithdrawn;

    @JsonProperty("last_history_id")
    private Long lastHistoryId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
