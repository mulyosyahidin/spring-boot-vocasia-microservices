package com.vocasia.order.dto.client.instructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
