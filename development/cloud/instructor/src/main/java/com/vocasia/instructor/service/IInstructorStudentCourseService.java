package com.vocasia.instructor.service;

import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import com.vocasia.instructor.request.AssignCourseToStudentInstructorRequest;

import java.util.List;

public interface IInstructorStudentCourseService {

    InstructorStudentCourse assignNewCourse(InstructorStudent instructorStudent, AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest);
    List<InstructorStudentCourse> getStudentCourses(InstructorStudent instructorStudent);

}
