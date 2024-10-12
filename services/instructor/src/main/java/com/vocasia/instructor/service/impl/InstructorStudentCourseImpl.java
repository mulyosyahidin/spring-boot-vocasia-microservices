package com.vocasia.instructor.service.impl;

import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import com.vocasia.instructor.repository.InstructorStudentCourseRepository;
import com.vocasia.instructor.request.AssignCourseToStudentInstructorRequest;
import com.vocasia.instructor.service.IInstructorStudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstructorStudentCourseImpl implements IInstructorStudentCourseService {

    private InstructorStudentCourseRepository instructorStudentCourseRepository;

    @Override
    public InstructorStudentCourse assignNewCourse(InstructorStudent instructorStudent, AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest) {
        InstructorStudentCourse instructorStudentCourse = new InstructorStudentCourse();

        instructorStudentCourse.setInstructorStudent(instructorStudent);
        instructorStudentCourse.setCourseId(assignCourseToStudentInstructorRequest.getCourseId());
        instructorStudentCourse.setOrderId(assignCourseToStudentInstructorRequest.getOrderId());

        return instructorStudentCourseRepository.save(instructorStudentCourse);
    }

    @Override
    public List<InstructorStudentCourse> getStudentCourses(InstructorStudent instructorStudent) {
        return instructorStudentCourseRepository.findAllByInstructorStudent(instructorStudent);
    }

}
