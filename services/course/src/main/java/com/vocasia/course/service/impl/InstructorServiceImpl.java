package com.vocasia.course.service.impl;

import com.vocasia.course.dto.feign.InstructorDto;
import com.vocasia.course.dto.feign.UserDto;
import com.vocasia.course.service.IInstructorService;
import com.vocasia.course.service.client.InstructorFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class InstructorServiceImpl implements IInstructorService {

    private InstructorFeignClient instructorFeignClient;

    private final Logger logger = LoggerFactory.getLogger(InstructorServiceImpl.class);

    @Override
    public InstructorDto getInstructorById(Long id) {
        try {
            ResponseEntity<Map<String, Object>> instructorProfileResponseEntity = instructorFeignClient.getInstructorById(id);
            logger.info("Response Entity: {}", instructorProfileResponseEntity);

            Map<String, Object> responseBody = instructorProfileResponseEntity.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> instructor = (Map<String, Object>) data.get("instructor");

                    InstructorDto instructorDto = new InstructorDto();

                    instructorDto.setId(id);
                    instructorDto.setStatus((String) instructor.get("status"));
                    instructorDto.setSummary((String) instructor.get("summary"));
                    instructorDto.setPhoneNumber((String) instructor.get("phone_number"));

                    Map<String, Object> user = (Map<String, Object>) instructor.get("user");

                    UserDto userDto = new UserDto();

                    userDto.setId((Integer) user.get("id"));
                    userDto.setUid((String) user.get("uid"));
                    userDto.setEmail((String) user.get("email"));
                    userDto.setUsername((String) user.get("username"));
                    userDto.setName((String) user.get("name"));
                    userDto.setRole((String) user.get("role"));
                    userDto.setProfilePicture((String) user.get("profile_picture"));
                    userDto.setProfilePictureUrl((String) user.get("profile_picture_url"));
                    userDto.setCreatedAt(LocalDateTime.parse(user.get("created_at").toString()));
                    userDto.setUpdatedAt(LocalDateTime.parse(user.get("updated_at").toString()));

                    instructorDto.setUser(userDto);
                    instructorDto.setUserId((Integer) instructor.get("user_id"));

                    return instructorDto;
                }
            } else {
                logger.warn("Feign failed: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            throw new RuntimeException("Failed to parse error response", e);
        }
    }
}
