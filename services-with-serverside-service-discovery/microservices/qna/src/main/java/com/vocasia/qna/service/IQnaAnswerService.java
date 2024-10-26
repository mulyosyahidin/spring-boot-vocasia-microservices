package com.vocasia.qna.service;

import com.vocasia.qna.entity.QnaAnswer;
import com.vocasia.qna.request.PostQnaAnswerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IQnaAnswerService {

    QnaAnswer save(Long qnaId, PostQnaAnswerRequest request);
    Page<QnaAnswer> findAllByQnaId(Long qnaId, Pageable paging);

}
