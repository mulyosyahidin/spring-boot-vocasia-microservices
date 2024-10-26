package com.vocasia.instructor.repository;

import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorStudentCourseRepository extends JpaRepository<InstructorStudentCourse, Long> {

    List<InstructorStudentCourse> findAllByInstructorStudent(InstructorStudent instructorStudent);

}
