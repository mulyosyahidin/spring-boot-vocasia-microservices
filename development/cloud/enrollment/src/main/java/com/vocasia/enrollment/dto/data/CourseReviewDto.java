package com.vocasia.enrollment.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseReviewDto {

    private Long id;

    @JsonProperty("enrollment_id")
    private Long enrollmentId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("user_id")
    private Long userId;

    private Integer rating;

    private String review;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
