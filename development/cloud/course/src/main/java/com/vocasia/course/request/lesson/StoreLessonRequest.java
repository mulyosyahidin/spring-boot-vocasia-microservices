package com.vocasia.course.request.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vocasia.course.validation.annotation.ValidLessonContent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ValidLessonContent
public class StoreLessonRequest {

    @NotBlank(message = "Judul pelajaran harus diisi")
    private String title;

    @NotBlank(message = "Jenis pelajaran harus diisi")
    private String type;

    @NotNull (message = "Urutan pelajaran harus diisi")
    @JsonProperty("need_previous_lesson")
    private Boolean needPreviousLesson;

    @NotNull (message = "Biaya pelajaran harus diisi")
    @JsonProperty("is_free")
    private Boolean isFree;

    @JsonProperty("content_video_duration")
    private String contentVideoDuration;

    @JsonProperty("content_video_url")
    private String contentVideoUrl;

    @JsonProperty("content_text")
    private String contentText;

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

