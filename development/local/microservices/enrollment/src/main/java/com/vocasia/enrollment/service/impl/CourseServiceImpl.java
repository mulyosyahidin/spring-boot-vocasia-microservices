package com.vocasia.enrollment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.enrollment.dto.ResponseDto;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.dto.client.course.CategoryDto;
import com.vocasia.enrollment.dto.client.course.CourseDto;
import com.vocasia.enrollment.dto.client.course.InstructorDto;
import com.vocasia.enrollment.exception.CustomFeignException;
import com.vocasia.enrollment.service.ICourseService;
import com.vocasia.enrollment.service.client.CourseFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseFeignClient courseFeignClient;

    @Cacheable(value = "courses", key = "#courseId")
    @Override
    public CourseDto findById(Long courseId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> courseFeignClientFindByIdResponseEntity = courseFeignClient.findById(correlationId, courseId);
            ResponseDto responseBody = courseFeignClientFindByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            Map<String, Object> course = data != null ? (Map<String, Object>) data.get("course") : null;
            Map<String, Object> category = data != null ? (Map<String, Object>) data.get("category") : null;
            Map<String, Object> instructor = data != null ? (Map<String, Object>) data.get("instructor") : null;

            logger.debug("CourseServiceImpl.findById() $course:: {}", course);
            logger.debug("CourseServiceImpl.findById() $category:: {}", category);
            logger.debug("CourseServiceImpl.findById() $instructor:: {}", instructor);

            CourseDto courseDto = new CourseDto();
            CategoryDto categoryDto = new CategoryDto();
            InstructorDto instructorDto = new InstructorDto();

            if (course != null) {
                courseDto.setId(Long.valueOf(course.get("id").toString()));
                courseDto.setInstructorId(Long.valueOf(course.get("instructor_id").toString()));
                courseDto.setTitle(course.get("title").toString());
                courseDto.setSlug(course.get("slug").toString());
                courseDto.setLevel(course.get("level").toString());
                courseDto.setLanguage(course.get("language").toString());
                courseDto.setDescription(course.get("description").toString());
                courseDto.setPrice(Double.valueOf(course.get("price").toString()));
                courseDto.setDiscount(Double.valueOf(course.get("discount").toString()));
                courseDto.setStatus(course.get("status").toString());
                courseDto.setRating(Double.valueOf(course.get("rating").toString()));
                courseDto.setTotalDuration(course.get("total_duration").toString());
                courseDto.setShortDescription(course.get("short_description").toString());
                courseDto.setFeaturedPicture(course.get("featured_picture").toString());
                courseDto.setFeaturedPictureUrl(course.get("featured_picture_url").toString());
                courseDto.setIsFree((Boolean) course.get("is_free"));
                courseDto.setIsDiscount((Boolean) course.get("is_discount"));
                courseDto.setChapterCount(Integer.valueOf(course.get("chapter_count").toString()));
                courseDto.setLessonCount(Integer.valueOf(course.get("lesson_count").toString()));
                courseDto.setRatingCount(Integer.valueOf(course.get("rating_count").toString()));
                courseDto.setEnrollmentCount(Integer.valueOf(course.get("enrollment_count").toString()));
                courseDto.setDeletedAt(course.get("deleted_at") != null ? LocalDateTime.parse(course.get("deleted_at").toString()) : null);
                courseDto.setCreatedAt(LocalDateTime.parse(course.get("created_at").toString()));
                courseDto.setUpdatedAt(LocalDateTime.parse(course.get("updated_at").toString()));
            }

            if (instructor != null) {
                instructorDto.setId(Long.valueOf(instructor.get("id").toString()));
                instructorDto.setUserId(Integer.valueOf(instructor.get("user_id").toString()));
                instructorDto.setStatus(instructor.get("status").toString());
                instructorDto.setSummary(instructor.get("summary").toString());
                instructorDto.setPhoneNumber(instructor.get("phone_number").toString());

                Map<String, Object> user = (Map<String, Object>) instructor.get("user");

                logger.debug("CourseServiceImpl.findById() $user:: {}", user);

                UserDto userDto = new UserDto();

                if (user != null) {
                    userDto.setId(Long.valueOf(user.get("id").toString()));
                    userDto.setEmail(user.get("email").toString());
                    userDto.setUsername(user.get("username").toString());
                    userDto.setName(user.get("name").toString());
                    userDto.setRole(user.get("role").toString());
                    userDto.setProfilePicture(user.get("profile_picture") != null ? user.get("profile_picture").toString() : null);
                    userDto.setProfilePictureUrl(user.get("profile_picture_url") != null ? user.get("profile_picture_url").toString() : null);

                    instructorDto.setUser(userDto);
                }

                courseDto.setInstructor(instructorDto);
            }

            if (category != null) {
                categoryDto.setId(Long.valueOf(category.get("id").toString()));
                categoryDto.setName(category.get("name").toString());
                categoryDto.setSlug(category.get("slug").toString());
                categoryDto.setParentId(category.get("parent_id") != null ? Long.valueOf(category.get("parent_id").toString()) : null);
                categoryDto.setIcon(category.get("icon") != null ? category.get("icon").toString() : null);
                categoryDto.setIconUrl(category.get("icon_url") != null ? category.get("icon_url").toString() : null);
                categoryDto.setCreatedAt(LocalDateTime.parse(category.get("created_at").toString()));
                categoryDto.setUpdatedAt(LocalDateTime.parse(category.get("updated_at").toString()));

                courseDto.setCategory(categoryDto);
            }

            return courseDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }
}
