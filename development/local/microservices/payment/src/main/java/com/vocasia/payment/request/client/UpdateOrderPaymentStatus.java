package com.vocasia.payment.request.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateOrderPaymentStatus {

    private String status;

    @JsonProperty("transaction_status")
    private String transactionStatus;

}
