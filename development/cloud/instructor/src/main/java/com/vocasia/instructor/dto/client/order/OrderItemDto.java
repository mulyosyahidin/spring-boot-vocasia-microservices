package com.vocasia.instructor.dto.client.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {

    private Long id;

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("course_instructor_id")
    private Long courseInstructorId;

    @JsonProperty("course_title")
    private String courseTitle;

    @JsonProperty("course_slug")
    private String courseSlug;

    @JsonProperty("course_featured_picture_url")
    private String courseFeaturedPictureUrl;

    @JsonProperty("course_price")
    private double coursePrice;

    @JsonProperty("course_is_free")
    private boolean courseIsFree;

    @JsonProperty("course_is_discount")
    private boolean courseIsDiscount;

    @JsonProperty("course_discount")
    private double courseDiscount;

    @JsonProperty("course_subtotal")
    private double courseSubtotal;

}
