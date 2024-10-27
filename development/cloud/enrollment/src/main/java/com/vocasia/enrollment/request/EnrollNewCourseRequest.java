package com.vocasia.enrollment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class EnrollNewCourseRequest {

    @NotNull(message = "User ID is required")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Order ID is required")
    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("enrollment_date")
    private LocalDateTime enrollmentDate;

    @NotNull(message = "Courses is required")
    @JsonProperty("courses")
    private List<CourseRequest> courses;

    @Getter
    @Setter
    public static class CourseRequest {
        @NotNull(message = "Course ID is required")
        @JsonProperty("course_id")
        private Long courseId;
    }

}
