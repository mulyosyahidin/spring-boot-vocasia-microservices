package com.vocasia.course.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LessonDto {

    private Long id;

    @JsonProperty("chapter_id")
    private Long chapterId;

    private String title;

    private String type;

    @JsonProperty("need_previous_lesson")
    private Boolean needPreviousLesson;

    @JsonProperty("is_free")
    private Boolean isFree;

    @JsonProperty("content_video_duration")
    private String contentVideoDuration;

    @JsonProperty("content_video_url")
    private String contentVideoUrl;

    @JsonProperty("content_text")
    private String contentText;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}

