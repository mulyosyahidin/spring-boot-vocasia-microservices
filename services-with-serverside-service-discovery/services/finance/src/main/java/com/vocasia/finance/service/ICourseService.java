package com.vocasia.finance.service;

import com.vocasia.finance.dto.client.course.CourseDto;

public interface ICourseService {

    CourseDto findById(Long courseId, String correlationId);

}
