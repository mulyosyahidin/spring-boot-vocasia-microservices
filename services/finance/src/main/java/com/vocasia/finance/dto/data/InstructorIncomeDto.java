package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class InstructorIncomeDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("total_user_payment")
    private Double totalUserPayment;

    @JsonProperty("platform_fee_in_percent")
    private BigDecimal platformFeeInPercent;

    @JsonProperty("total_platform_fee")
    private Double totalPlatformFee;

    @JsonProperty("total_instructor_income")
    private Double totalInstructorIncome;

    private String remarks;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

