package com.vocasia.order.dto.client.instructor;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class InstructorStudentDto {

    private Long id;

    @JsonProperty("instructor_id")
    private Long instructorId;

    @JsonProperty("user_id")
    private Long userId;

}

