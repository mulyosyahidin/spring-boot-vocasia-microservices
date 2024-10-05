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

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_category_course"))
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String slug;

    private String totalDuration;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'all'")
    private String level = "all";

    @Column(nullable = false)
    private String language;

    @Lob
    private String description;

    @Column(nullable = false)
    private String shortDescription;

    private String featuredPicture;

    @Column(nullable = false)
    private Double price = 0.0;

    @Column(nullable = false)
    private Boolean isFree = false;

    @Column(nullable = false)
    private Boolean isDiscount = false;

    private Double discount = 0.0;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'draft'")
    private String status = "draft";

    private LocalDateTime deletedAt;

}
