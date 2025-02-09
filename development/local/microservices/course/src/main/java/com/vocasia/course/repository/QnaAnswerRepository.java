package com.vocasia.course.repository;

import com.vocasia.course.entity.QnaAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QnaAnswerRepository extends JpaRepository<QnaAnswer, Long> {

    Page<QnaAnswer> findAllByQnaId(Long qnaId, Pageable paging);
    List<QnaAnswer> findAllByQnaId(Long qnaId);

}
