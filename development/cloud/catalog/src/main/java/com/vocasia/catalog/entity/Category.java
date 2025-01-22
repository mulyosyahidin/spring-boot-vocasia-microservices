package com.vocasia.catalog.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "categories")
public class Category {

    @Id
    @Field(name = "_category_id")
    private ObjectId categoryId;

    private Long id;
    private String type;

    @Field(name = "parent_id")
    private Long parentId;

    private String name;
    private String slug;
    private String icon;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;

    private Parent parent;
    private List<Children> children;

    @Data
    public static class Parent {

        @Id
        @Field(name = "_parent_category_id")
        private ObjectId parentCategoryId;

        private Long id;
        private String name;
        private String slug;
        private String icon;

        @Field(name = "created_at")
        private LocalDateTime createdAt;

        @Field(name = "updated_at")
        private LocalDateTime updatedAt;

    }

    @Data
    public static class Children {

        @Id
        @Field(name = "_children_category_id")
        private ObjectId childrenCategoryId;

        private Long id;
        private String name;
        private String slug;
        private String icon;

        @Field(name = "created_at")
        private LocalDateTime createdAt;

        @Field(name = "updated_at")
        private LocalDateTime updatedAt;

    }

}
