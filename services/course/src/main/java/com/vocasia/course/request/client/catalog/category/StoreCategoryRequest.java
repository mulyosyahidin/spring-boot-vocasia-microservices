package com.vocasia.course.request.client.catalog.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class StoreCategoryRequest {

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
