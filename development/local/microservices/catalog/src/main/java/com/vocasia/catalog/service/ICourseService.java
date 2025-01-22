package com.vocasia.catalog.service;

import com.vocasia.catalog.entity.Course;
import com.vocasia.catalog.request.course.StoreCourseRequest;

import java.util.List;

public interface ICourseService {

    Course save(StoreCourseRequest storeCourseRequest);
    List<Course> findAll();
    List<Course> findLatestCourses();
    Course findById(Long courseId);

}
