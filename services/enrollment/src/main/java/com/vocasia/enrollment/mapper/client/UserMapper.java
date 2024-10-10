package com.vocasia.enrollment.mapper.client;

import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.entity.client.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUid(user.getUid());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        userDto.setProfilePicture(user.getProfilePicture());
        userDto.setProfilePictureUrl(user.getProfilePictureUrl());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

    public static User mapToEntity(UserDto userDto) {
        User user = new User();

        user.setId(userDto.getId());
        user.setUid(userDto.getUid());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setRole(userDto.getRole());
        user.setProfilePicture(userDto.getProfilePicture());
        user.setProfilePictureUrl(userDto.getProfilePictureUrl());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setUpdatedAt(userDto.getUpdatedAt());

        return user;
    }

}
