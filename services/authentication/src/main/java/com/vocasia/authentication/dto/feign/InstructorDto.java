package com.vocasia.authentication.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorDto {

    private Integer id;
    private String status;
    private String summary;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
