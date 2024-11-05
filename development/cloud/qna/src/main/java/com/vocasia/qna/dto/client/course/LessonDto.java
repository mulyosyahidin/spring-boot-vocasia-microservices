package com.vocasia.qna.dto.client.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class LessonDto implements Serializable {

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

    @JsonProperty("has_attachment")
    private Boolean hasAttachment;

    @JsonProperty("attachment_type")
    private String attachmentType;

    @JsonProperty("attachment_file_url")
    private String attachmentFileUrl;

    @JsonProperty("attachment_file_name")
    private String attachmentFileName;

    @JsonProperty("attachment_link")
    private String attachmentLink;

    @JsonProperty("attachment_link_name")
    private String attachmentLinkName;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
