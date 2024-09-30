package com.vocasia.course.repository;

import com.vocasia.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByStatus(String draft);
    List<Course> findByInstructorIdAndStatus(Long instructorId, String draft);

}
