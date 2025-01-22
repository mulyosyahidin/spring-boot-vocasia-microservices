package com.vocasia.enrollment.repository;

import com.vocasia.enrollment.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {

    boolean existsByEnrollmentIdAndLessonId(Long enrollmentId, Long lessonId);
    Progress findByEnrollmentIdAndLessonId(Long id, Long lessonId);
    int countByEnrollmentIdAndStatus(Long id, String string);

}
