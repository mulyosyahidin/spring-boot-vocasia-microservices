package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.exception.ResourceNotFoundException;
import com.vocasia.enrollment.repository.EnrollmentRepository;
import com.vocasia.enrollment.request.EnrollNewCourseRequest;
import com.vocasia.enrollment.service.IEnrollmentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public void enrollCourse(EnrollNewCourseRequest enrollNewCourseRequest) {
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
    }

    @Override
    public Enrollment findById(Long userId, Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findByUserIdAndId(userId, enrollmentId);

        if (enrollment == null) {
            throw new ResourceNotFoundException("Data tidak ditemukan");
        }

        return enrollment;
    }

    @Override
    public boolean isUserEnrolled(Long userId, Long courseId) {
        return enrollmentRepository.existsByUserIdAndCourseId(userId, courseId);
    }

    @Override
    public Page<Enrollment> findAllByCourseId(Long courseId, Pageable paging) {
        return enrollmentRepository.findAllByCourseId(courseId, paging);
    }

    @Override
    public Page<Enrollment> findAllByUserId(Long userId, Pageable paging) {
        return enrollmentRepository.findAllByUserId(userId, paging);
    }

    @Override
    public Enrollment findById(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public void update(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

}
