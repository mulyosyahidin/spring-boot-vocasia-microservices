package com.vocasia.enrollment.service;

import com.vocasia.enrollment.entity.CourseReview;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.request.PostCourseReviewRequest;

public interface ICourseReview {

    void save(Enrollment enrollment, PostCourseReviewRequest request);
    CourseReview findByEnrollmentId(Long id);

}
