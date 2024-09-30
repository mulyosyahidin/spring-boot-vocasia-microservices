package com.vocasia.course.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vocasia.course.dto.feign.InstructorDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    private InstructorDto instructor;

    private CategoryDto category;
    private String title;
    private String slug;

    @JsonProperty("total_duration")
    private String totalDuration;

    private String level;
    private String language;
    private String description;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("featured_picture")
    private String featuredPicture;

    @JsonProperty("featured_picture_url")
    private String featuredPictureUrl;

    private Double price;

    @JsonProperty("is_free")
    private Boolean isFree;

    @JsonProperty("is_discount")
    private Boolean isDiscount;

    private Double discount;

    private String status;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
