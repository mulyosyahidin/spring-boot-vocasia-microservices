package com.vocasia.enrollment.repository;

import com.vocasia.enrollment.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUserId(Long userId);
    Enrollment findByUserIdAndId(Long userId, Long enrollmentId);
    List<Enrollment> findByCourseId(Long courseId);

}
