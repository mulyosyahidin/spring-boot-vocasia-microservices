package com.vocasia.qna.mapper;

import com.vocasia.qna.dto.data.QnaAnswerDto;
import com.vocasia.qna.entity.QnaAnswer;
import org.springframework.stereotype.Component;

@Component
public class QnaAnswerMapper {

    public static QnaAnswerDto mapToDto(QnaAnswer entity) {
        QnaAnswerDto dto = new QnaAnswerDto();

        dto.setId(entity.getId());
        dto.setQnaId(entity.getQnaId());
        dto.setUserId(entity.getUserId());
        dto.setAnswer(entity.getAnswer());
        dto.setIsInstructor(entity.getIsInstructor());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

}
