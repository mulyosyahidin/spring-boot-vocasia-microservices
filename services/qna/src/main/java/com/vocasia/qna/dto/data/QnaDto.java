package com.vocasia.qna.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QnaDto {

    private Long id;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("user_id")
    private Long userId;

    private String title;
    private String question;

    @JsonProperty("short_question")
    private String shortQuestion;

    @JsonProperty("is_solved")
    private boolean isSolved;

    @JsonProperty("solved_at")
    private LocalDateTime solvedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
