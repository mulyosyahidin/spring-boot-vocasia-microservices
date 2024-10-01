package com.vocasia.course.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ChapterDto {

    private Long id;

    @JsonProperty("course_id")
    private Long courseId;

    private String title;

    @JsonProperty("total_duration")
    private String totalDuration;

    List<LessonDto> lessons;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
