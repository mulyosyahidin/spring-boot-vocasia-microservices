package com.vocasia.catalog.request.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UpdateCategoryRequest {

    private Long id;

    @NotBlank(message = "Type tidak boleh kosong")
    private String type;

    @JsonProperty("parent_id")
    private Long parentId;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String name;

    @NotBlank(message = "Slug tidak boleh kosong")
    private String slug;

    private String icon;

    @NotNull(message = "Created at tidak boleh kosong")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @NotNull(message = "Updated at tidak boleh kosong")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    private Parent parent;

    private List<Children> children;

    @Setter
    @Getter
    public static class Parent {
        private Long id;
        private String type;

        @JsonProperty("parent_id")
        private Long parentId;

        private String name;
        private String slug;
        private String icon;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;
    }

    @Setter
    @Getter
    public static class Children {
        private Long id;
        private String type;

        @JsonProperty("parent_id")
        private Long parentId;

        private String name;
        private String slug;
        private String icon;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;
    }

}
