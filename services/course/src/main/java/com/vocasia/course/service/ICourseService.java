package com.vocasia.course.service;

import com.vocasia.course.entity.Course;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;

import java.io.IOException;
import java.util.List;

public interface ICourseService {

    Course store(CreateNewCourseRequest createNewCourseRequest);
    Course updateThumbnail(Course course, UpdateCourseThumbnailRequest updateCourseThumbnailRequest) throws IOException;
    Course show(Long courseId);

    List<Course> getDraftCourses();
    List<Course> getDraftCoursesByInstructorId(Long instructorId);

    Course updateCourse(Long courseId, UpdateCourseRequest updateCourseRequest);

}
