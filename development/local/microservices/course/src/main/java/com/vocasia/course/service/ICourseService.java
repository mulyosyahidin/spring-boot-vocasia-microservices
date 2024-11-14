package com.vocasia.course.service;

import com.vocasia.course.dto.data.LessonDto;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ICourseService {

    Course save(Long instructorId, CreateNewCourseRequest createNewCourseRequest);
    Course findById(Long courseId);
    Course update(Course course, UpdateCourseRequest updateCourseRequest);
    Course updateCourseThumbnail(Course course, UpdateCourseThumbnailRequest updateCourseThumbnailRequest) throws IOException;

    Course publishCourseById(Course course);

    List<Course> getEditorsChoices();

    Integer chapterCount(Long courseId);
    Integer lessonCount(Long courseId);

    Double rating(Long courseId);
    Integer ratingCount(Long courseId);
    Integer enrollmentCount(Long courseId);

    Page<Course> findAllByInstructorId(Long instructorId, Pageable paging);
    Page<Course> findAllByInstructorIdAndStatus(Long instructorId, String status, Pageable paging);

    Page<Course> findAllPublishedByInstructorId(Long instructorId, Pageable paging);
    Page<Course> findAllDraftByInstructorId(Long instructorId, Pageable paging);

    int countByStatusAndInstructorId(String status, Long instructorId);

    Page<Course> findAll(Pageable paging);

    long count();
    long countByStatus(String status);
    long countByIsFree(boolean b);

}
