package com.vocasia.catalog.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CourseDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("category_id")
    private Long categoryId;

    private String title;
    private String slug;

    @JsonProperty("total_duration")
    private String totalDuration;

    private String level = "all";
    private String language;
    private String description;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("featured_picture")
    private String featuredPicture;

    @JsonProperty("featured_picture_url")
    private String featuredPictureUrl;

    private Double price = 0.0;

    @JsonProperty("is_free")
    private Boolean isFree = false;

    @JsonProperty("is_discount")
    private Boolean isDiscount = false;

    private Double discount = 0.0;
    private String status = "draft";

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    private Category category;
    private Instructor instructor;
    private List<Chapter> chapters;

    @Setter
    @Getter
    public static class Category {
        private Long id;
        private String type;
        private String name;
        private String slug;
        private String icon;

        @JsonProperty("icon_url")
        private String iconUrl;

        @JsonProperty("created_at")
        private LocalDateTime createdAt;

        @JsonProperty("updated_at")
        private LocalDateTime updatedAt;
    }

    @Setter
    @Getter
    public static class Instructor {

        private Long id;

        @JsonProperty("user_id")
        private Long userId;

        private String status = "pending";

        @JsonProperty("phone_number")
        private String phoneNumber;

        private String summary;

        private User user;

        @Setter
        @Getter
        public static class User {

            private Long id;
            private String email;
            private String username;
            private String name;

            @JsonProperty("profile_picture")
            private String profilePicture;

            @JsonProperty("profile_picture_url")
            private String profilePictureUrl;
        }
    }

    @Setter
    @Getter
    public static class Chapter {

        private Long id;
        private String title;

        @JsonProperty("total_duration")
        private String totalDuration;

        private List<Lesson> lessons;

        @Setter
        @Getter
        public static class Lesson {

            private Long id;
            private String title;
            private String type;

            @JsonProperty("need_previous_lesson")
            private Boolean needPreviousLesson = true;

            @JsonProperty("is_free")
            private Boolean isFree = false;

            @JsonProperty("content_video_duration")
            private String contentVideoDuration;

            @JsonProperty("content_video_url")
            private String contentVideoUrl;

            @JsonProperty("content_text")
            private String contentText;

            @JsonProperty("attachment_type")
            private String attachmentType;

            @JsonProperty("attachment_file_url")
            private String attachmentFileUrl;

            @JsonProperty("attachment_file_name")
            private String attachmentFileName;

            @JsonProperty("attachment_link")
            private String attachmentLink;

            @JsonProperty("attachment_link_name")
            private String attachmentLinkName;
        }
    }

}
