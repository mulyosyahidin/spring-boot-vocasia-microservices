package com.vocasia.course.request.client.catalog.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class StoreCourseRequest {

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

        }

    }
    
    @Setter
    @Getter
    public static class Chapter {
        
        private Long id;
        private String title;

        @JsonProperty("total_duration")
        private String totalDuration;
        
        List<Lesson> lessons;
        
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
