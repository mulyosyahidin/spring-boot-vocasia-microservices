package com.vocasia.course.service;

import com.vocasia.course.entity.Course;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;

import java.io.IOException;
import java.util.List;

public interface ICourseService {

    Course save(CreateNewCourseRequest createNewCourseRequest);
    Course findById(Long courseId);
    Course update(Course course, UpdateCourseRequest updateCourseRequest);
    Course updateCourseThumbnail(Course course, UpdateCourseThumbnailRequest updateCourseThumbnailRequest) throws IOException;

    List<Course> getDraftCourses();
    List<Course> getDraftCoursesByInstructorId(Long instructorId);

    List<Course> getAllCourses();
    List<Course> getAllCoursesByInstructorId(Long instructorId);

    List<Course> getPublishedCourses();
    List<Course> getPublishedCoursesByInstructorId(Long instructorId);

    Course publishCourseById(Course course);

    List<Course> getEditorsChoices();

    Integer chapterCount(Long courseId);
    Integer lessonCount(Long courseId);

    Double rating(Long courseId);
    Integer ratingCount(Long courseId);
    Integer enrollmentCount(Long courseId);

}
