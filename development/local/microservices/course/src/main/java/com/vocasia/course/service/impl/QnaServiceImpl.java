package com.vocasia.course.service.impl;

import com.vocasia.course.entity.Qna;
import com.vocasia.course.repository.QnaRepository;
import com.vocasia.course.request.qna.AskQuestionRequest;
import com.vocasia.course.service.IQnaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;

@Service
@AllArgsConstructor
public class QnaServiceImpl implements IQnaService {

    private QnaRepository qnaRepository;

    @Override
    public boolean existsByUserIdAndLessonId(Long userId, Long lessonId) {
        return qnaRepository.existsByUserIdAndLessonId(userId, lessonId);
    }

    @Override
    public Page<Qna> findAllByLessonId(Long lessonId, Pageable paging) {
        return qnaRepository.findAllByLessonId(lessonId, paging);
    }

    @Override
    public Page<Qna> findAllByCourseId(Long courseId, Pageable paging) {
        return qnaRepository.findAllByCourseId(courseId, paging);
    }

    @Override
    public Qna save(Long userId, AskQuestionRequest request) {
        Qna qna = new Qna();

        qna.setUserId(userId);
        qna.setCourseId(request.getCourseId());
        qna.setLessonId(request.getLessonId());
        qna.setTitle(request.getTitle());
        qna.setQuestion(request.getQuestion());

        return qnaRepository.save(qna);
    }

    @Override
    public Qna findById(Long qnaId) {
        return qnaRepository.findById(qnaId)
                .orElseThrow(() -> new ResolutionException("Data tidak ditemukan"));
    }

    @Override
    public List<Qna> findAllByUserIdAndLessonId(Long userId, Long lessonId) {
        return qnaRepository.findAllByUserIdAndLessonId(userId, lessonId);
    }

}