package com.vocasia.course.dto.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private Integer id;
    private String email;
    private String username;
    private String name;
    private String role;

    @JsonProperty("profile_picture")
    private String profilePicture;

    @JsonProperty("profile_picture_url")
    private String profilePictureUrl;

}
