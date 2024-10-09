package com.vocasia.enrollment.service;

import com.vocasia.enrollment.dto.client.course.CourseDto;

public interface ICourseService {

    CourseDto getCourseById(Long courseId);

}
