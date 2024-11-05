package com.vocasia.enrollment.mapper;

import com.vocasia.enrollment.dto.data.CourseReviewDto;
import com.vocasia.enrollment.entity.CourseReview;
import org.springframework.stereotype.Component;

@Component
public class CourseReviewMapper {

    public static CourseReviewDto mapToDto(CourseReview courseReview) {
        CourseReviewDto courseReviewDto = new CourseReviewDto();

        courseReviewDto.setId(courseReview.getId());
        courseReviewDto.setEnrollmentId(courseReview.getEnrollmentId());
        courseReviewDto.setCourseId(courseReview.getCourseId());
        courseReviewDto.setUserId(courseReview.getUserId());
        courseReviewDto.setRating(courseReview.getRating());
        courseReviewDto.setReview(courseReview.getReview());
        courseReviewDto.setCreatedAt(courseReview.getCreatedAt());
        courseReviewDto.setUpdatedAt(courseReview.getUpdatedAt());

        return courseReviewDto;
    }

}
