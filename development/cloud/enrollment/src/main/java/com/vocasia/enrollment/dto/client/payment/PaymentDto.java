package com.vocasia.enrollment.dto.client.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class PaymentDto implements Serializable {

    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("order_number")
    private String orderNumber;

    @JsonProperty("total_price")
    private Double totalPrice;

    @JsonProperty("additional_fee")
    private Double additionalFee;

    @JsonProperty("total_payment")
    private Double totalPayment;

    @JsonProperty("snap_token")
    private String snapToken;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("payment_expire_at")
    private LocalDateTime paymentExpireAt;

    @JsonProperty("is_expired")
    private Boolean isExpired;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
