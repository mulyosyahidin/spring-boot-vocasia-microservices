package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.course.CourseDto;

public interface ICourseService {

    CourseDto findById(Long courseId, String correlationId);

}
