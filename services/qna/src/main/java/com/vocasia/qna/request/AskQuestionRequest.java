package com.vocasia.qna.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AskQuestionRequest {

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @NotBlank(message = "Judul pertanyaan harus diisi")
    private String title;

    @NotBlank(message = "Pertanyaan harus diisi")
    private String question;

}
