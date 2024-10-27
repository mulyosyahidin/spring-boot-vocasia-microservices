package com.vocasia.instructor.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InstuctorDto {

    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    private String summary;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String status;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

}
