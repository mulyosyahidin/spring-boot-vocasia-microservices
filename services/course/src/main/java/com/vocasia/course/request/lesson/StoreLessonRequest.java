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

    private String contentText;

    @JsonProperty("content_file_url")
    private String contentFileUrl;

    @JsonProperty("content_file_name")
    private String contentFileName;

    @JsonProperty("content_file_size")
    private Integer contentFileSize;

    @JsonProperty("content_file_type")
    private String contentFileType;

}

