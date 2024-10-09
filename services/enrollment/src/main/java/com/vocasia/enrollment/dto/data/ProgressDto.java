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

    @JsonProperty("watched_duration")
    private String watchedDuration;

    @JsonProperty("last_accessed")
    private LocalDateTime lastAccessed;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

