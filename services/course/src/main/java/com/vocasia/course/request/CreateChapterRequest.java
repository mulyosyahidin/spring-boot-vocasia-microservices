package com.vocasia.course.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateChapterRequest {

    @NotBlank(message = "Judul chapter harus diisi")
    private String title;

    @NotBlank(message = "Total durasi harus diisi")
    @JsonProperty("total_duration")
    private String totalDuration;

}
