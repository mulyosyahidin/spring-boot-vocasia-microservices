package com.vocasia.authentication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String uid;
    private String email;
    private String username;
    private String name;
    private String role;

    @JsonProperty("profile_picture")
    private String profilePicture;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

}
