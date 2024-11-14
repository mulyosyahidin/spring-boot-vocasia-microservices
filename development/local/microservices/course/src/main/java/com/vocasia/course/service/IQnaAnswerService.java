package com.vocasia.course.service;

import com.vocasia.course.entity.QnaAnswer;
import com.vocasia.course.request.qna.PostQnaAnswerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQnaAnswerService {

    QnaAnswer save(Long qnaId, PostQnaAnswerRequest request);
    Page<QnaAnswer> findAllByQnaId(Long qnaId, Pageable paging);

}
