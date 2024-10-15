package com.vocasia.enrollment.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProgressDto {

    private Long id;

    @JsonProperty("enrollment_id")
    private Long enrollmentId;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("started_at")
    private LocalDateTime startedAt;

    @JsonProperty("completed_at")
    private LocalDateTime completedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

