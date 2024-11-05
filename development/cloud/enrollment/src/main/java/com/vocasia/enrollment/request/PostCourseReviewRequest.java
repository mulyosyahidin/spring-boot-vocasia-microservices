package com.vocasia.enrollment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostCourseReviewRequest {

    @NotNull(message = "Rating is required")
    private int rating;

    @NotBlank(message = "Review is required")
    private String review;

}
