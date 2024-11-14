package com.vocasia.course.mapper;

import com.vocasia.course.dto.data.QnaDto;
import com.vocasia.course.entity.Qna;
import org.springframework.stereotype.Component;

@Component
public class QnaMapper {

    public static QnaDto mapToDto(Qna entity) {
        QnaDto dto = new QnaDto();

        String question = entity.getQuestion();
        String cleanQuestion = question.replaceAll("<[^>]*>", "");
        String shortQuestion = cleanQuestion.length() > 120 ? cleanQuestion.substring(0, 120) + "..." : cleanQuestion;

        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setCourseId(entity.getCourseId());
        dto.setLessonId(entity.getLessonId());
        dto.setTitle(entity.getTitle());
        dto.setQuestion(entity.getQuestion());
        dto.setShortQuestion(shortQuestion);
        dto.setSolved(entity.isSolved());
        dto.setSolvedAt(entity.getSolvedAt());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

}