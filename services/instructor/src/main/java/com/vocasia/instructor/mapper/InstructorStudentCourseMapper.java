package com.vocasia.instructor.mapper;

import com.vocasia.instructor.dto.data.InstructorStudentCourseDto;
import com.vocasia.instructor.entity.InstructorStudent;
import com.vocasia.instructor.entity.InstructorStudentCourse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorStudentCourseMapper {

    public static InstructorStudentCourseDto mapToDto(InstructorStudentCourse instructorStudentCourse) {
        InstructorStudentCourseDto instructorStudentCourseDto = new InstructorStudentCourseDto();

        instructorStudentCourseDto.setId(instructorStudentCourse.getId());
        instructorStudentCourseDto.setInstructorStudentId(instructorStudentCourse.getInstructorStudent().getId());
        instructorStudentCourseDto.setCourseId(instructorStudentCourse.getCourseId());
        instructorStudentCourseDto.setOrderId(instructorStudentCourse.getOrderId());

        return instructorStudentCourseDto;
    }

    public static InstructorStudentCourse mapToEntity(InstructorStudentCourseDto instructorStudentCourseDto, InstructorStudent instructorStudent) {
        InstructorStudentCourse instructorStudentCourse = new InstructorStudentCourse();

        instructorStudentCourse.setId(instructorStudentCourseDto.getId());
        instructorStudentCourse.setInstructorStudent(instructorStudent);
        instructorStudentCourse.setCourseId(instructorStudentCourseDto.getCourseId());
        instructorStudentCourse.setOrderId(instructorStudentCourseDto.getOrderId());

        return instructorStudentCourse;
    }

}
