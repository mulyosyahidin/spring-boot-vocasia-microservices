package com.vocasia.enrollment.service;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;

import java.util.List;

public interface IEnrollmentService {

    void enrollCourse(EnrollNewCourseRequest enrollNewCourseRequest);
    Enrollment findById(Long userId, Long enrollmentId);
    List<Enrollment> getUserEnrolledCourse(Long userId);
    List<Enrollment> getUserEnrolledCourseByCourseId(String correlationId, Long courseId);

    boolean isUserEnrolled(Long userId, Long courseId);

}
