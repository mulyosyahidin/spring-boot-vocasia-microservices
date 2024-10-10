package com.vocasia.enrollment.service;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.client.User;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;

import java.util.List;

public interface IEnrollmentService {

    List<Enrollment> enrollCourse(EnrollNewCourseRequest enrollNewCourseRequest);
    List<Enrollment> getUserEnrolledCourse(Long userId);
    Enrollment getEnrollmentDetail(Long userId, Long enrollmentId);
    List<Enrollment> getUserEnrolledCourseByCourseId(String correlationId, Long courseId);

}
