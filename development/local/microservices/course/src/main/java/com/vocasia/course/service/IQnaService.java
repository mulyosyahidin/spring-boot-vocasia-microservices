package com.vocasia.course.service;

import com.vocasia.course.entity.Qna;
import com.vocasia.course.request.qna.AskQuestionRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQnaService {

    boolean existsByUserIdAndLessonId(Long userId, Long lessonId);

    Page<Qna> findAllByLessonId(Long lessonId, Pageable paging);
    Page<Qna> findAllByCourseId(Long courseId, Pageable paging);

    Qna save(Long userId, @Valid AskQuestionRequest request);
    Qna findById(Long qnaId);

    List<Qna> findAllByUserIdAndLessonId(Long userId, Long lessonId);
}
