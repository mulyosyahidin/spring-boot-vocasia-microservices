package com.vocasia.finance.dto.client.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrderDto implements Serializable {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("total_items")
    private int totalItems;

    @JsonProperty("total_price")
    private double totalPrice;

    @JsonProperty("total_discount")
    private double totalDiscount;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}
