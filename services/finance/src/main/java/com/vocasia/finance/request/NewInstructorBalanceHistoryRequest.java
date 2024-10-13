package com.vocasia.finance.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewInstructorBalanceHistoryRequest {

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("platform_fee")
    private Double platformFee;

    @JsonProperty("reference_id")
    private Long referenceId;

    @JsonProperty("reference_type")
    private String referenceType;

    @JsonProperty("transaction_status")
    private String transactionStatus;

    @JsonProperty("remarks")
    private String remarks;

}
