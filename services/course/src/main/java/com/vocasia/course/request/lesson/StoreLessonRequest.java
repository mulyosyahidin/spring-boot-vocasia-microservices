package com.vocasia.course.request.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreLessonRequest {

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

}

