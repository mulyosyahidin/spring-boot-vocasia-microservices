package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.entity.Enrollment;
import com.vocasia.enrollment.entity.Progress;
import com.vocasia.enrollment.repository.ProgressRepository;
import com.vocasia.enrollment.service.IProgressService;
import com.vocasia.enrollment.types.ProgressStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProgressServiceImpl implements IProgressService {

    private final ProgressRepository progressRepository;

    @Override
    public boolean existsByEnrollmentIdAndLessonId(Long enrollmentId, Long lessonId) {
        return progressRepository.existsByEnrollmentIdAndLessonId(enrollmentId, lessonId);
    }

    @Override
    public void save(Enrollment enrollment, Long lessonId) {
        Progress progress = new Progress();

        progress.setEnrollmentId(enrollment.getId());
        progress.setLessonId(lessonId);
        progress.setStartedAt(LocalDateTime.now());
        progress.setStatus(ProgressStatus.IN_PROGRESS.toString());

        progressRepository.save(progress);
    }

    @Override
    public Progress findByEnrollmentIdAndLessonId(Long id, Long lessonId) {
        return progressRepository.findByEnrollmentIdAndLessonId(id, lessonId);
    }

    @Override
    public void update(Progress progress) {
        progressRepository.save(progress);
    }

    @Override
    public int countByEnrollmentIdAndStatus(Long id, String string) {
        return progressRepository.countByEnrollmentIdAndStatus(id, string);
    }

}
