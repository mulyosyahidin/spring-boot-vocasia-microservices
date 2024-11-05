package com.vocasia.authentication.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ForgetPasswordDto {

    private Long id;
    private String email;
    private String token;

    @JsonProperty("is_expired")
    private boolean isExpired;

    @JsonProperty("expired_at")
    private LocalDateTime expiredAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
