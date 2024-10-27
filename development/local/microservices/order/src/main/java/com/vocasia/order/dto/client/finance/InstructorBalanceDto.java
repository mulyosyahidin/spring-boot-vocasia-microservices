package com.vocasia.order.dto.client.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class InstructorBalanceDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("current_balance")
    private Double currentBalance;

    @JsonProperty("total_income")
    private Double totalIncome;

    @JsonProperty("total_pending_withdrawal")
    private Double totalPendingWithdrawal;

    @JsonProperty("total_withdrawn")
    private Double totalWithdrawn;

    @JsonProperty("total_platform_fee")
    private Double totalPlatformFee;

    @JsonProperty("last_history_id")
    private Long lastHistoryId;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}