package com.vocasia.instructor.mapper;

import com.vocasia.instructor.dto.client.authentication.UserDto;

import java.util.HashMap;
import java.util.Map;

public class UserMapper {

    public static Map<String, Object> mapUserToResponse(UserDto user) {
        Map<String, Object> userData = new HashMap<>();

        userData.put("id", user.getId());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("name", user.getName());
        userData.put("role", user.getRole());
        userData.put("profile_picture", user.getProfilePicture());

        return userData;
    }

}
