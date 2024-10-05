package com.vocasia.order.request.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderPaymentRequest {

    @NotNull(message = "Order ID cannot be null")
    @JsonProperty("order_id")
    private Long orderId;

    @NotBlank(message = "Order number cannot be blank")
    @JsonProperty("order_number")
    private String orderNumber;

    @NotNull(message = "Total price cannot be null")
    @JsonProperty("total_price")
    private Double totalPrice;

}

