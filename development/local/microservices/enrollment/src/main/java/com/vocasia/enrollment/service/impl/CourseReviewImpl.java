package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.entity.CourseReview;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.repository.CourseReviewRepository;
import com.vocasia.enrollment.request.PostCourseReviewRequest;
import com.vocasia.enrollment.service.ICourseReview;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseReviewImpl implements ICourseReview {

    private final CourseReviewRepository courseReviewRepository;

    @Override
    public void save(Enrollment enrollment, PostCourseReviewRequest request) {
        CourseReview courseReview = new CourseReview();

        courseReview.setEnrollmentId(enrollment.getId());
        courseReview.setCourseId(enrollment.getCourseId());
        courseReview.setUserId(enrollment.getUserId());
        courseReview.setRating(request.getRating());
        courseReview.setReview(request.getReview());

        courseReviewRepository.save(courseReview);
    }

    @Override
    public CourseReview findByEnrollmentId(Long id) {
        return courseReviewRepository.findByEnrollmentId(id);
    }

}
