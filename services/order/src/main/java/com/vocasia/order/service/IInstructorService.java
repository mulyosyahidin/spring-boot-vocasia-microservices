package com.vocasia.order.service;

import com.vocasia.order.dto.client.instructor.InstructorStudentCourseDto;
import com.vocasia.order.request.client.instructor.AssignCourseToStudentInstructorRequest;

public interface IInstructorService {

    InstructorStudentCourseDto assignCourse(AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest, String correlationId);

}
