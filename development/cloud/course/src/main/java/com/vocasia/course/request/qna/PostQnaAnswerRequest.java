package com.vocasia.course.request.qna;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostQnaAnswerRequest {

    @NotBlank(message = "Jawaban tidak boleh kosong")
    private String answer;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_instructor")
    private Boolean isInstructor;

}
