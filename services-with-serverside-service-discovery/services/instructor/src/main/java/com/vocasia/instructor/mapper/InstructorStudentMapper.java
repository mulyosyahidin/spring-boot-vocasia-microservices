package com.vocasia.instructor.mapper;

import com.vocasia.instructor.dto.data.InstructorStudentDto;
import com.vocasia.instructor.entity.InstructorStudent;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorStudentMapper {

    public static InstructorStudentDto mapToDto(InstructorStudent instructorStudent) {
        InstructorStudentDto instructorStudentDto = new InstructorStudentDto();

        instructorStudentDto.setId(instructorStudent.getId());
        instructorStudentDto.setInstructorId(instructorStudent.getInstructorId());
        instructorStudentDto.setUserId(instructorStudent.getUserId());

        return instructorStudentDto;
    }

    public static InstructorStudent mapToEntity(InstructorStudentDto instructorStudentDto) {
        InstructorStudent instructorStudent = new InstructorStudent();

        instructorStudent.setId(instructorStudentDto.getId());
        instructorStudent.setInstructorId(instructorStudentDto.getInstructorId());
        instructorStudent.setUserId(instructorStudentDto.getUserId());

        return instructorStudent;
    }

}
