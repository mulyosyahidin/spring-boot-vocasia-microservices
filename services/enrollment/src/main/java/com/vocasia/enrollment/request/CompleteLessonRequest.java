package com.vocasia.enrollment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompleteLessonRequest {

    @NotNull(message = "total lesson count is required")
    @JsonProperty("total_lesson")
    private Integer totalLesson;

}
