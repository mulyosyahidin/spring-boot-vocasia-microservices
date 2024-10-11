package com.vocasia.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourseRequest {

    @NotBlank(message = "Judul kursus harus diisi")
    private String title;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("category_id")
    private String categoryId;

    private String level;
    private String language;
    private Double price;
    private Double discount;
    private String description;

    @JsonProperty("total_duration")
    private String totalDuration;

}
