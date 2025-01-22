package com.vocasia.course.service.impl;

import com.vocasia.course.entity.QnaAnswer;
import com.vocasia.course.repository.QnaAnswerRepository;
import com.vocasia.course.request.qna.PostQnaAnswerRequest;
import com.vocasia.course.service.IQnaAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QnaAnswerServiceImpl implements IQnaAnswerService {

    private QnaAnswerRepository qnaAnswerRepository;

    @Override
    public Page<QnaAnswer> findAllByQnaId(Long qnaId, Pageable paging) {
        return qnaAnswerRepository.findAllByQnaId(qnaId, paging);
    }

    @Override
    public QnaAnswer save(Long qnaId, PostQnaAnswerRequest request) {
        QnaAnswer qnaAnswer = new QnaAnswer();

        qnaAnswer.setQnaId(qnaId);
        qnaAnswer.setUserId(request.getUserId());
        qnaAnswer.setAnswer(request.getAnswer());
        qnaAnswer.setIsInstructor(request.getIsInstructor());

        return qnaAnswerRepository.save(qnaAnswer);
    }

}