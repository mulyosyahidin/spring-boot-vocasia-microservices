package com.vocasia.course.dto.client.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {

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
