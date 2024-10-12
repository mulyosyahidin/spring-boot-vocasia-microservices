package com.vocasia.instructor.service;

import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.request.RegisterStudentRequest;

import java.util.List;

public interface IInstructorStudentService {

    boolean isStudentRegistered(RegisterStudentRequest registerStudentRequest);
    InstructorStudent registerStudent(RegisterStudentRequest registerStudentRequest);
    InstructorStudent findStudentByUserId(Long instructorId, Long userId);
    List<InstructorStudent> findAllByInstructorId(Long instructorId);
    InstructorStudent findById(Long instructorStudentId);

}
