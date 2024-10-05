package com.vocasia.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "course_title", nullable = false)
    private String courseTitle;

    @Column(name = "course_slug", nullable = false)
    private String courseSlug;

    @Column(name = "course_featured_picture_url", nullable = false)
    private String courseFeaturedPictureUrl;

    @Column(name = "course_price", nullable = false)
    private double coursePrice;

    @Column(name = "course_is_free", nullable = false)
    private boolean courseIsFree;

    @Column(name = "course_is_discount", nullable = false)
    private boolean courseIsDiscount;

    @Column(name = "course_discount", nullable = true)
    private double courseDiscount;

    @Column(name = "course_subtotal", nullable = false)
    private double courseSubtotal;

}

