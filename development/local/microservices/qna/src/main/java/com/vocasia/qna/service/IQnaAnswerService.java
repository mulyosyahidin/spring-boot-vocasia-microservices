package com.vocasia.qna.service;

import com.vocasia.qna.entity.QnaAnswer;
import com.vocasia.qna.request.PostQnaAnswerRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IQnaAnswerService {

    QnaAnswer save(Long qnaId, PostQnaAnswerRequest request);
    Page<QnaAnswer> findAllByQnaId(Long qnaId, Pageable paging);

}
