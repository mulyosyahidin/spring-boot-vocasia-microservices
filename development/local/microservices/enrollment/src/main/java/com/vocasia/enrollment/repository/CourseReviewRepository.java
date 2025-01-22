package com.vocasia.enrollment.repository;

import com.vocasia.enrollment.entity.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseReviewRepository extends JpaRepository<CourseReview, Long> {

    CourseReview findByEnrollmentId(Long id);

}
