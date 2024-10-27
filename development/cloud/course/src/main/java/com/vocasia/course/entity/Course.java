package com.vocasia.course.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
@ToString(callSuper = true)
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long instructorId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String slug;

    @Column(name = "total_duration")
    private String totalDuration;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'all'")
    private String level = "all";

    @Column(nullable = false)
    private String language;

    @Lob
    private String description;

    @Lob
    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "featured_picture")
    private String featuredPicture;

    @Column(nullable = false)
    private Double price = 0.0;

    @Column(name = "is_free", nullable = false)
    private Boolean isFree = false;

    @Column(name = "is_discount", nullable = false)
    private Boolean isDiscount = false;

    private Double discount = 0.0;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'draft'")
    private String status = "draft";

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

}
