package com.vocasia.order.request.client.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewPlatformIncomeRequest {

    @NotNull(message = "Instructor ID is required")
    @Positive(message = "Instructor ID must be a positive number")
    @JsonProperty("instructor_id")
    private Long instructorId;

    @NotNull(message = "Order ID is required")
    @Positive(message = "Order ID must be a positive number")
    @JsonProperty("order_id")
    private Long orderId;

    @NotNull(message = "Course ID is required")
    @Positive(message = "Course ID must be a positive number")
    @JsonProperty("course_id")
    private Long courseId;

    @NotNull(message = "Total user payment is required")
    @PositiveOrZero(message = "Total user payment must be zero or a positive number")
    @JsonProperty("total_user_payment")
    private Double totalUserPayment;

    @Size(max = 255, message = "Remarks can have at most 255 characters")
    private String remarks;

}
