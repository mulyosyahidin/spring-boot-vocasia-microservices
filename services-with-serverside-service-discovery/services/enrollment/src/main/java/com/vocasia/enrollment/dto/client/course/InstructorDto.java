package com.vocasia.enrollment.dto.client.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorDto {

    private Long id;

    @JsonProperty("user_id")
    private Integer userId;

    private UserDto user;

    private String status;
    private String summary;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
