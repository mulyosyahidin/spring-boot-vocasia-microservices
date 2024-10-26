package com.vocasia.catalog.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

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

}
