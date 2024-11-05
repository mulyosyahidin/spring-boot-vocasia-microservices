package com.vocasia.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UpdateContentRequest {

    @JsonProperty("total_duration")
    private String totalDuration;

    private List<VideoContent> videos;

    @Setter
    @Getter
    public static class VideoContent {
        private String title;
        private String duration;
        private String url;
    }

}
