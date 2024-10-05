package com.vocasia.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.slugify.Slugify;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNewCourseRequest {

    @NotBlank(message = "Judul kursus harus diisi")
    private String title;

    @NotNull(message = "ID Instruktur harus diisi")
    @JsonProperty("instructor_id")
    private Long instructorId;

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

    public String getSlug() {
        final Slugify slg = Slugify.builder().build();

        return title != null ? slg.slugify(title)
                : null;
    }

}
