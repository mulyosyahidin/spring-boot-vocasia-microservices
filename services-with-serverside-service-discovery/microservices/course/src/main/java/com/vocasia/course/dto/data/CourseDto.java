package com.vocasia.course.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseDto {

    private Long id;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("instructor_id")
    private Long instructorId;

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

    @JsonProperty("chapter_count")
    private Integer chapterCount;

    @JsonProperty("lesson_count")
    private Integer lessonCount;

    private Double rating;

    @JsonProperty("rating_count")
    private Integer ratingCount;

    @JsonProperty("enrollment_count")
    private Integer enrollmentCount;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
