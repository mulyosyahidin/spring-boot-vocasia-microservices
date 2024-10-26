package com.vocasia.instructor.mapper;

import com.vocasia.instructor.dto.data.InstuctorDto;
import com.vocasia.instructor.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    public static InstuctorDto mapToDto(Instructor instructor) {
        InstuctorDto instuctorDto = new InstuctorDto();

        instuctorDto.setId(instructor.getId());
        instuctorDto.setUserId(instructor.getUserId());
        instuctorDto.setStatus(instructor.getStatus());
        instuctorDto.setPhoneNumber(instructor.getPhoneNumber());
        instuctorDto.setSummary(instructor.getSummary());
        instuctorDto.setDeletedAt(instructor.getDeletedAt());

        return instuctorDto;
    }

}
