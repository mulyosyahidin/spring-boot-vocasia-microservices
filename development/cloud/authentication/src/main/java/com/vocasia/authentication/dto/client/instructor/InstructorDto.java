package com.vocasia.authentication.dto.client.instructor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class InstructorDto implements Serializable {

    private Integer id;
    private String status;
    private String summary;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
