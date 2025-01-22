package com.vocasia.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.slugify.Slugify;
import com.vocasia.course.validation.annotation.CreateValidDiscount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CreateValidDiscount
public class CreateNewCourseRequest {

    @NotBlank(message = "Judul kursus harus diisi")
    private String title;

    @NotNull(message = "Kategori kursus harus diisi")
    @JsonProperty("category_id")
    private Long categoryId;

    @NotBlank(message = "Level kursus harus diisi")
    private String level;

    @NotBlank(message = "Bahasa kursus harus diisi")
    private String language;

    @NotNull(message = "Harga kursus harus diisi")
    private Double price;

    private Double discount;

    @JsonProperty("short_description")
    private String shortDescription;

    private String description;

    @NotBlank(message = "Total durasi kursus harus diisi")
    @JsonProperty("total_duration")
    private String totalDuration;

    public String getSlug() {
        final Slugify slg = Slugify.builder().build();

        return title != null ? slg.slugify(title)
                : null;
    }

}
