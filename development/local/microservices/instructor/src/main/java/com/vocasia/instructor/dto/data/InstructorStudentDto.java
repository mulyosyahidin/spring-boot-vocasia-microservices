package com.vocasia.instructor.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorStudentDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("user_id")
    private Long userId;

}
