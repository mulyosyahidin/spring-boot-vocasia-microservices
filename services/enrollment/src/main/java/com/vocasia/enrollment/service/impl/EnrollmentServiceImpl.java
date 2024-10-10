package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.client.User;
import com.vocasia.enrollment.repository.EnrollmentRepository;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;
import com.vocasia.enrollment.service.IAuthenticationService;
import com.vocasia.enrollment.service.IEnrollmentService;
import com.vocasia.enrollment.service.client.AuthenticationFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EnrollmentServiceImpl implements IEnrollmentService {

    private final Logger logger = LoggerFactory.getLogger(EnrollmentServiceImpl.class);

    private final EnrollmentRepository enrollmentRepository;
    private final IAuthenticationService authenticationService;

    @Override
    public List<Enrollment> enrollCourse(EnrollNewCourseRequest enrollNewCourseRequest) {
        List<EnrollNewCourseRequest.CourseRequest> enrollCourses = enrollNewCourseRequest.getCourses();
        List<Enrollment> enrollments = new ArrayList<>();

        LocalDateTime currentDate = LocalDateTime.now();

        for (EnrollNewCourseRequest.CourseRequest course : enrollCourses) {
            Enrollment enrollment = new Enrollment();

            enrollment.setUserId(enrollNewCourseRequest.getUserId());
            enrollment.setOrderId(enrollNewCourseRequest.getOrderId());
            enrollment.setCourseId(course.getCourseId());
            enrollment.setEnrollmentDate(currentDate);
            enrollment.setStatus("active");
            enrollment.setProgressPercentage(BigDecimal.valueOf(0.00));

            enrollmentRepository.save(enrollment);

            enrollments.add(enrollment);
        }

        return enrollments;
    }

    @Override
    public List<Enrollment> getUserEnrolledCourse(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }

    @Override
    public Enrollment getEnrollmentDetail(Long userId, Long enrollmentId) {
        return enrollmentRepository.findByUserIdAndId(userId, enrollmentId);
    }

    @Override
    public List<Enrollment> getUserEnrolledCourseByCourseId(String correlationId, Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }

}
