package com.vocasia.enrollment.service;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.Progress;

public interface IProgressService {

    boolean existsByEnrollmentIdAndLessonId(Long enrollmentId, Long lessonId);
    void save(Enrollment enrollment, Long lessonId);

    Progress findByEnrollmentIdAndLessonId(Long id, Long lessonId);

    void update(Progress progress);
    int countByEnrollmentIdAndStatus(Long id, String string);

}
