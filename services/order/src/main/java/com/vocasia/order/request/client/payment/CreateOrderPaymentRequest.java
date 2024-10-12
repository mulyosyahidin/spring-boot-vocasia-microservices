package com.vocasia.order.request.client.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vocasia.order.request.PlaceNewOrderRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @NotNull(message = "Customer data cannot be null")
    private PlaceNewOrderRequest.Customer customer;

    @Getter
    @Setter
    public static class Customer {

        @NotNull(message = "Customer ID cannot be null")

        private Long id;

        @NotNull(message = "Customer name cannot be null")
        private String name;

        @NotNull(message = "Customer email cannot be null")
        private String email;

    }

    @NotNull(message = "Order items cannot be null")
    private List<CreateOrderPaymentRequest.Item> items;

    @Getter
    @Setter
    public static class Item {

        @NotNull(message = "Course ID cannot be null")
        @JsonProperty("course_id")
        private Long courseId;

        @NotNull(message = "Course title cannot be null")
        @JsonProperty("course_title")
        private String courseTitle;

        @NotNull(message = "Course price cannot be null")
        @JsonProperty("course_price")
        private Double coursePrice;

        @JsonProperty("course_discount")
        private Double courseDiscount;

    }

}

