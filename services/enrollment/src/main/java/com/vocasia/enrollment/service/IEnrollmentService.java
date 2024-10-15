package com.vocasia.enrollment.service;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEnrollmentService {

    void enrollCourse(EnrollNewCourseRequest enrollNewCourseRequest);
    Enrollment findById(Long userId, Long enrollmentId);

    boolean isUserEnrolled(Long userId, Long courseId);

    Page<Enrollment> findAllByCourseId(Long courseId, Pageable paging);
    Page<Enrollment> findAllByUserId(Long userId, Pageable paging);

    Enrollment findById(Long enrollmentId);

    void update(Enrollment enrollment);
}
