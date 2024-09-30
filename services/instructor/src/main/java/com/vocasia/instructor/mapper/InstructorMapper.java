package com.vocasia.instructor.mapper;

import com.vocasia.instructor.dto.data.InstuctorDto;
import com.vocasia.instructor.dto.feign.UserDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    private static IAuthenticationService authenticationService;

    @Autowired
    public InstructorMapper(IAuthenticationService authenticationService, ApplicationContext applicationContext) {
        InstructorMapper.authenticationService = authenticationService;
    }

    public static InstuctorDto mapToDto(Instructor instructor) {
        InstuctorDto instuctorDto = new InstuctorDto();

        instuctorDto.setId(instructor.getId());
        instuctorDto.setUserId(instructor.getUserId());
        instuctorDto.setStatus(instructor.getStatus());
        instuctorDto.setPhoneNumber(instructor.getPhoneNumber());
        instuctorDto.setSummary(instructor.getSummary());
        instuctorDto.setDeletedAt(instructor.getDeletedAt());

        try {
            UserDto userDto = authenticationService.getByUserId(instructor.getUserId());
            instuctorDto.setUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();

            instuctorDto.setUser(null);
        }

        return instuctorDto;
    }

    public static Instructor mapToEntity(InstuctorDto instuctorDto) {
        Instructor instructor = new Instructor();

        instructor.setId(instuctorDto.getId());
        instructor.setUserId(instuctorDto.getUserId());
        instructor.setStatus(instuctorDto.getStatus());
        instructor.setPhoneNumber(instuctorDto.getPhoneNumber());
        instructor.setSummary(instuctorDto.getSummary());
        instructor.setDeletedAt(instuctorDto.getDeletedAt());

        return instructor;
    }

}
