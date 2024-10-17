package com.vocasia.course.request.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateLessonRequest {

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

    @JsonProperty("remove_attachment")
    private Boolean removeAttachment;

    @JsonProperty("attachment_type")
    private String attachmentType;

    @JsonProperty("attachment_file_name")
    private String attachmentFileName;

    @JsonProperty("attachment_file")
    private MultipartFile attachmentFile;

    @JsonProperty("attachment_link")
    private String attachmentLink;

    @JsonProperty("attachment_link_name")
    private String attachmentLinkName;

}

