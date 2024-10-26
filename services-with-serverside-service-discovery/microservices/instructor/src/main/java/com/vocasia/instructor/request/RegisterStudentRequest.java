package com.vocasia.instructor.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterStudentRequest {

    @NotNull(message = "Instructor ID cannot be null")
    @JsonProperty("instructor_id")
    private Long instructorId;

    @NotNull(message = "Student ID cannot be null")
    @JsonProperty("user_id")
    private Long userId;

}
