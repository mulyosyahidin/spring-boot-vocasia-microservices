package com.vocasia.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DummyMidtransCallbackRequest {

    @JsonProperty("order_id")
    private String orderId;

}
