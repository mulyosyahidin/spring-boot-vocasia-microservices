package com.vocasia.qna.repository;

import com.vocasia.qna.entity.Qna;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaRepository extends JpaRepository<Qna, Long> {

    boolean existsByUserIdAndLessonId(Long userId, Long lessonId);

    Page<Qna> findAllByLessonId(Long lessonId, Pageable paging);
    Page<Qna> findAllByCourseId(Long courseId, Pageable paging);
    List<Qna> findAllByUserIdAndLessonId(Long userId, Long lessonId);
}
