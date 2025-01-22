package com.vocasia.authentication.mapper;

import com.vocasia.authentication.dto.data.ForgetPasswordDto;
import com.vocasia.authentication.entity.ForgetPassword;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ForgetPasswordMapper {

    public static ForgetPasswordDto mapToDto(ForgetPassword forgetPassword) {
        ForgetPasswordDto forgetPasswordDto = new ForgetPasswordDto();

        forgetPasswordDto.setId(forgetPassword.getId());
        forgetPasswordDto.setEmail(forgetPassword.getEmail());
        forgetPasswordDto.setToken(forgetPassword.getToken());
        forgetPasswordDto.setExpiredAt(forgetPassword.getExpiredAt());
        forgetPasswordDto.setCreatedAt(forgetPassword.getCreatedAt());
        forgetPasswordDto.setUpdatedAt(forgetPassword.getUpdatedAt());
        forgetPasswordDto.setExpired(forgetPassword.getExpiredAt().isBefore(LocalDateTime.now()));

        return forgetPasswordDto;
    }

    public static ForgetPassword mapToEntity(ForgetPasswordDto forgetPasswordDto) {
        ForgetPassword forgetPassword = new ForgetPassword();

        forgetPassword.setId(forgetPasswordDto.getId());
        forgetPassword.setEmail(forgetPasswordDto.getEmail());
        forgetPassword.setToken(forgetPasswordDto.getToken());
        forgetPassword.setExpiredAt(forgetPasswordDto.getExpiredAt());
        forgetPassword.setCreatedAt(forgetPasswordDto.getCreatedAt());
        forgetPassword.setUpdatedAt(forgetPasswordDto.getUpdatedAt());

        return forgetPassword;
    }

}
