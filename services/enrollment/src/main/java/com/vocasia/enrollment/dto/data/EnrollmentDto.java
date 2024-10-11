package com.vocasia.enrollment.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("enrollment_date")
    private LocalDateTime enrollmentDate;

    @JsonProperty("status")
    private String status;

    @JsonProperty("progress_percentage")
    private BigDecimal progressPercentage;

    @JsonProperty("completion_date")
    private LocalDateTime completionDate;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

