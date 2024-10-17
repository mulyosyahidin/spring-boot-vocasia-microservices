package com.vocasia.qna.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.qna.dto.ResponseDto;
import com.vocasia.qna.dto.client.authentication.UserDto;
import com.vocasia.qna.dto.client.course.LessonDto;
import com.vocasia.qna.exception.CustomFeignException;
import com.vocasia.qna.service.ICourseService;
import com.vocasia.qna.service.client.CourseFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private CourseFeignClient courseFeignClient;

    @Override
    public LessonDto findLessonById(Long id, String correlationId) {
        try {
            ResponseEntity<ResponseDto> courseFeignClientLessonByIdResponseEntity = courseFeignClient.findLessonById(correlationId, id);
            ResponseDto responseBody = courseFeignClientLessonByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> lesson = data != null ? (Map<String, Object>) data.get("lesson") : null;

            logger.debug("CourseServiceImpl.findLessonById() $lesson:: {}", lesson);

            LessonDto lessonDto = new LessonDto();

            if (lesson != null) {
                lessonDto.setId(Long.valueOf(lesson.get("id").toString()));
                lessonDto.setTitle(lesson.get("title").toString());
            }

            return lessonDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
