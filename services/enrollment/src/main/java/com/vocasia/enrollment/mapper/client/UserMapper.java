package com.vocasia.enrollment.mapper.client;

import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.entity.client.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto mapToDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        userDto.setUsername(user.getUsername());
        userDto.setName(user.getName());
        userDto.setRole(user.getRole());
        userDto.setProfilePicture(user.getProfilePicture());
        userDto.setProfilePictureUrl(user.getProfilePictureUrl());

        return userDto;
    }

}
