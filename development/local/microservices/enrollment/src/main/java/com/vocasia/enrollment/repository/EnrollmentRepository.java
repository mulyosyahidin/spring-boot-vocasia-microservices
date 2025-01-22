package com.vocasia.enrollment.repository;

import com.vocasia.enrollment.entity.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByUserId(Long userId);
    Enrollment findByUserIdAndId(Long userId, Long enrollmentId);
    boolean existsByUserIdAndCourseId(Long userId, Long courseId);

    Page<Enrollment> findAllByCourseId(Long courseId, Pageable paging);
    Page<Enrollment> findAllByUserId(Long userId, Pageable paging);

}
