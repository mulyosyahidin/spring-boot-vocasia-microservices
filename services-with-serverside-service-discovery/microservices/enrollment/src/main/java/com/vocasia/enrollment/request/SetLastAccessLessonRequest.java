package com.vocasia.enrollment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SetLastAccessLessonRequest {

    @NotNull(message = "lessonId is required")
    @JsonProperty("lesson_id")
    private Long lessonId;

}
