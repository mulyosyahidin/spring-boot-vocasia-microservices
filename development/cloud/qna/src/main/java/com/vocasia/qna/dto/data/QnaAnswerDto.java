package com.vocasia.qna.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QnaAnswerDto {

    private Long id;

    @JsonProperty("qna_id")
    private Long qnaId;

    @JsonProperty("user_id")
    private Long userId;

    private String answer;

    @JsonProperty("is_instructor")
    private Boolean isInstructor;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
