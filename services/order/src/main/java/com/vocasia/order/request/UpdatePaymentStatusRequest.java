package com.vocasia.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePaymentStatusRequest {

    private String status;

    @JsonProperty("transaction_status")
    private String transactionStatus;

}
