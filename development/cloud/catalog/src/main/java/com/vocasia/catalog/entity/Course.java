package com.vocasia.catalog.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "courses")
public class Course {

    @Id
    @Field(name = "_course_id")
    private ObjectId courseId;

    @Field(name = "id")
    private Long id;

    @Field(name = "instructor_id")
    private Long instructorId;

    @Field(name = "category_id")
    private Long categoryId;

    private String title;
    private String slug;

    @Field(name = "total_duration")
    private String totalDuration;

    private String level = "all";
    private String language;
    private String description;

    @Field(name = "short_description")
    private String shortDescription;

    @Field(name = "featured_picture")
    private String featuredPicture;

    private Double price = 0.0;

    @Field(name = "is_free")
    private Boolean isFree = false;

    @Field(name = "is_discount")
    private Boolean isDiscount = false;

    private Double discount = 0.0;
    private String status = "draft";

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    @Field(name = "deleted_at")
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

        @Field(name = "created_at")
        private LocalDateTime createdAt;

        @Field(name = "updated_at")
        private LocalDateTime updatedAt;

    }

    @Setter
    @Getter
    public static class Instructor {

        private Long id;

        @Field(name = "user_id")
        private Long userId;

        private String status = "pending";

        @Field(name = "phone_number")
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

            @Field(name = "profile_picture")
            private String profilePicture;

        }

    }

    @Setter
    @Getter
    public static class Chapter {

        private Long id;
        private String title;

        @Field(name = "total_duration")
        private String totalDuration;
        
        List<Lesson> lessons;
        
        @Setter
        @Getter
        public static class Lesson {
            
            private Long id;
            private String title;
            private String type;

            @Field(name = "need_previous_lesson")
            private Boolean needPreviousLesson = true;

            @Field(name = "is_free")
            private Boolean isFree = false;

            @Field(name = "content_video_duration")
            private String contentVideoDuration;

            @Field(name = "content_video_url")
            private String contentVideoUrl;

            @Field(name = "content_text")
            private String contentText;

            @Field(name = "attachment_type")
            private String attachmentType;

            @Field(name = "attachment_file_url")
            private String attachmentFileUrl;

            @Field(name = "attachment_file_name")
            private String attachmentFileName;

            @Field(name = "attachment_link")
            private String attachmentLink;

            @Field(name = "attachment_link_name")
            private String attachmentLinkName;

        }

    }

}
