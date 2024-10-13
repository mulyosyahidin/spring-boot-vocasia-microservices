package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WithdrawalRequestDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    private Double amount;
    private String status;

    @JsonProperty("requested_at")
    private LocalDateTime requestedAt;

    @JsonProperty("processed_at")
    private LocalDateTime processedAt;

    @JsonProperty("bank_name")
    private String bankName;

    @JsonProperty("bank_account_name")
    private String bankAccountName;

    @JsonProperty("bank_account_number")
    private String bankAccountNumber;

    private String remarks;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
