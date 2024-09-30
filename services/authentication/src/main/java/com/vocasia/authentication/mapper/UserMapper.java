package com.vocasia.authentication.mapper;

import com.vocasia.authentication.dto.UserDto;
import com.vocasia.authentication.entity.User;

import java.time.LocalDateTime;

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
        userDto.setCreatedAt(user.getCreatedAt().toString());
        userDto.setUpdatedAt(user.getUpdatedAt().toString());

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
        user.setCreatedAt(LocalDateTime.parse(userDto.getCreatedAt()));
        user.setUpdatedAt(LocalDateTime.parse(userDto.getUpdatedAt()));

        return user;
    }
}
