package com.vocasia.course.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.client.authentication.UserDto;
import com.vocasia.course.dto.client.instructor.InstructorDto;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.service.IInstructorService;
import com.vocasia.course.service.client.InstructorFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private InstructorFeignClient instructorFeignClient;

    private final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Override
    public InstructorDto getInstructorById(Long id, String correlationId) {
        try {
            ResponseEntity<ResponseDto> instructorFeignClientGetInstructorByIdResponseEntity = instructorFeignClient.getInstructorById(correlationId, id);
            ResponseDto responseBody = instructorFeignClientGetInstructorByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> instructor = data != null ? (Map<String, Object>) data.get("instructor") : null;
            Map<String, Object> user = data != null ? (Map<String, Object>) data.get("user") : null;

            logger.debug("InstructorServiceImpl.getInstructorById() $instructor:: {}", instructor);
            logger.debug("InstructorServiceImpl.getInstructorById() $user:: {}", user);

            InstructorDto instructorDto = new InstructorDto();
            UserDto userDto = new UserDto();

            if (instructor != null) {
                instructorDto.setId(id);
                instructorDto.setStatus((String) instructor.get("status"));
                instructorDto.setSummary((String) instructor.get("summary"));
                instructorDto.setPhoneNumber((String) instructor.get("phone_number"));
            }

            if (user != null) {
                userDto.setId((Integer) user.get("id"));
                userDto.setEmail((String) user.get("email"));
                userDto.setUsername((String) user.get("username"));
                userDto.setName((String) user.get("name"));
                userDto.setRole((String) user.get("role"));
                userDto.setProfilePicture((String) user.get("profile_picture"));
                userDto.setProfilePictureUrl((String) user.get("profile_picture_url"));

                instructorDto.setUser(userDto);
                instructorDto.setUserId((Integer) user.get("id"));
            }

            return instructorDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }
}
