package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.course.CourseDto;
import com.vocasia.instructor.dto.client.course.InstructorOverviewDto;

public interface ICourseService {

    CourseDto findById(Long courseId, String correlationId);
    InstructorOverviewDto getInstructorOverview(Long instructorId, String correlationId);

}
