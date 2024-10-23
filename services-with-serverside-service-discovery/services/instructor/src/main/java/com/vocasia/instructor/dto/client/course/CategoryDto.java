package com.vocasia.instructor.dto.client.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDto {

    private Long id;
    private String name;
    private String slug;

    @JsonProperty("parent_id")
    private Long parentId;

    private String icon;

    @JsonProperty("icon_url")
    private String iconUrl;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
