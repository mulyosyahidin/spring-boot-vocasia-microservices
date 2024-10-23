package com.vocasia.qna.service.client;

import com.vocasia.qna.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "course", url = "http://course:14122")
public interface CourseFeignClient {

    @GetMapping(value = "/api/instructor/courses/lessons/{lessonId}", consumes = "application/json")
    public ResponseEntity<ResponseDto> findLessonById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable("lessonId") Long userId);

}
