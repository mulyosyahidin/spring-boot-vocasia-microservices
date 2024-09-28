package com.vocasia.authentication.mapper;

import com.vocasia.authentication.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserMapper {

    public static Map<String, Object> mapUserToResponse(User user) {
        Map<String, Object> userData = new HashMap<>();

        userData.put("id", user.getId());
        userData.put("uid", user.getUid());
        userData.put("username", user.getUsername());
        userData.put("email", user.getEmail());
        userData.put("name", user.getName());
        userData.put("role", user.getRole());
        userData.put("profile_picture", user.getProfilePicture());
        userData.put("created_at", user.getCreatedAt());
        userData.put("updated_at", user.getUpdatedAt());

        return userData;
    }

}
