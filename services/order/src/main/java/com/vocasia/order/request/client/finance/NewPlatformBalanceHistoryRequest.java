package com.vocasia.order.request.client.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewPlatformBalanceHistoryRequest {

    @JsonProperty("type")
    private String type;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("reference_id")
    private Long referenceId;

    @JsonProperty("reference_type")
    private String referenceType;

    @JsonProperty("transaction_status")
    private String transactionStatus;

    @JsonProperty("remarks")
    private String remarks;

}
