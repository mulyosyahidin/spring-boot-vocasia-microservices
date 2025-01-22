package com.vocasia.course.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.slugify.Slugify;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateCategoryRequest {

    @NotBlank(message = "Nama kategori tidak boleh kosong")
    private String name;

    @NotNull(message = "File icon harus diisi")
    private MultipartFile icon;

    @JsonProperty("parent_id")
    private Long parentId;

    public String getSlug() {
        final Slugify slg = Slugify.builder().build();

        return name != null ? slg.slugify(name)
                : null;
    }

}
