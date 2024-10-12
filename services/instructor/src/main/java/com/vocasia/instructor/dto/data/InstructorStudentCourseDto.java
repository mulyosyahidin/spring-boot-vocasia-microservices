package com.vocasia.instructor.dto.data;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class InstructorStudentCourseDto {

    private Long id;

    @JsonProperty("instructor_student_id")
    private Long instructorStudentId;

    @JsonProperty("course_id")
    private Long courseId;

    @JsonProperty("order_id")
    private Long orderId;

}
