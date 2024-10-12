package com.vocasia.order.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceNewOrderRequest {

    @NotNull(message = "User ID cannot be null")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Customer data cannot be null")
    private Customer customer;

    @NotNull(message = "Order items cannot be null")
    private List<OrderItem> items;

    @Getter
    @Setter
    public static class Customer {

        @NotNull(message = "Customer ID cannot be null")

        private Long id;

        @NotNull(message = "Customer name cannot be null")
        private String name;

        @NotNull(message = "Customer email cannot be null")
        private String email;

    }

    @Getter
    @Setter
    public static class OrderItem {

        @NotNull(message = "Course ID cannot be null")
        @JsonProperty("course_instructor_id")
        private Long courseInstructorId;

        @NotNull(message = "Course ID cannot be null")
        @JsonProperty("course_id")
        private Long courseId;

        @NotNull(message = "Course title cannot be null")
        @JsonProperty("course_title")
        private String courseTitle;

        @NotNull(message = "Course slug cannot be null")
        @JsonProperty("course_slug")
        private String courseSlug;

        @NotNull(message = "Course featured picture URL cannot be null")
        @JsonProperty("course_featured_picture_url")
        private String courseFeaturedPictureUrl;

        @NotNull(message = "Course price cannot be null")
        @JsonProperty("course_price")
        private Double coursePrice;

        @NotNull(message = "Course is_free cannot be null")
        @JsonProperty("course_is_free")
        private Boolean courseIsFree;

        @NotNull(message = "Course is_discount cannot be null")
        @JsonProperty("course_is_discount")
        private Boolean courseIsDiscount;

        @JsonProperty("course_discount")
        private Double courseDiscount;

    }

}

