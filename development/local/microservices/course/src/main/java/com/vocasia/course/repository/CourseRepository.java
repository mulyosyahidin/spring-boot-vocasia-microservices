package com.vocasia.course.repository;

import com.vocasia.course.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(String draft);
    List<Course> findByInstructorIdAndStatus(Long instructorId, String draft);

    Page<Course> findAllByInstructorId(Long instructorId, Pageable paging);
    Page<Course> findAllByInstructorIdAndStatus(Long instructorId, String status, Pageable paging);

    Page<Course> findAllPublishedByInstructorId(Long instructorId, Pageable paging);
    Page<Course> findAllDraftByInstructorId(Long instructorId, Pageable paging);

    int countByStatusAndInstructorId(String status, Long instructorId);

}
