package com.vocasia.qna.service;

import com.vocasia.qna.dto.client.course.LessonDto;

public interface ICourseService {

    LessonDto findLessonById(Long id, String correlationId);

}
