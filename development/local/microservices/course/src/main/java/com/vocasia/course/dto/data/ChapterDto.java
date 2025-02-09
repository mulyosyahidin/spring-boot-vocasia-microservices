package com.vocasia.course.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChapterDto {

    private Long id;

    @JsonProperty("course_id")
    private Long courseId;

    private String title;

    @JsonProperty("total_duration")
    private String totalDuration;

    @JsonProperty("lesson_count")
    Integer lessonCount = 0;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
