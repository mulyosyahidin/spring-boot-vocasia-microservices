package com.vocasia.course.request.client.catalog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateCategoryRequest {

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
