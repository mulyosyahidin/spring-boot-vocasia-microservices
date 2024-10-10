package com.vocasia.enrollment.service.impl;

import com.vocasia.enrollment.dto.client.course.CategoryDto;
import com.vocasia.enrollment.dto.client.course.CourseDto;
import com.vocasia.enrollment.dto.client.course.InstructorDto;
import com.vocasia.enrollment.dto.client.authentication.UserDto;
import com.vocasia.enrollment.service.ICourseService;
import com.vocasia.enrollment.service.client.CourseFeignClient;
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
public class CourseServiceImpl implements ICourseService {

    private final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseFeignClient courseFeignClient;

    @Override
    public CourseDto getCourseById(Long courseId) {
        try {
            ResponseEntity<Map<String, Object>> courseResponseEntity = courseFeignClient.getCourseById(courseId);

            Map<String, Object> responseBody = courseResponseEntity.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> course = (Map<String, Object>) data.get("course");

//                    logger.info("Course data: {}", course);

                    if (course != null) {
                        CourseDto courseDto = new CourseDto();

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

                        Map<String, Object> instructor = (Map<String, Object>) course.get("instructor");

                        if (instructor != null) {
//                            logger.info("Instructor data: {}", instructor);

                            InstructorDto instructorDto = new InstructorDto();

                            instructorDto.setId(Long.valueOf(instructor.get("id").toString()));
                            instructorDto.setUserId(Integer.valueOf(instructor.get("user_id").toString()));
                            instructorDto.setStatus(instructor.get("status").toString());
                            instructorDto.setSummary(instructor.get("summary").toString());
                            instructorDto.setPhoneNumber(instructor.get("phone_number").toString());

                            Map<String, Object> user = (Map<String, Object>) instructor.get("user");

                            if (user != null) {
                                UserDto userDto = new UserDto();

                                userDto.setId(Long.valueOf(user.get("id").toString()));
                                userDto.setUid(user.get("uid").toString());
                                userDto.setEmail(user.get("email").toString());
                                userDto.setUsername(user.get("username").toString());
                                userDto.setName(user.get("name").toString());
                                userDto.setRole(user.get("role").toString());
                                userDto.setProfilePicture(user.get("profile_picture").toString());
                                userDto.setProfilePictureUrl(user.get("profile_picture_url").toString());
                                userDto.setCreatedAt(LocalDateTime.parse(user.get("created_at").toString()));
                                userDto.setUpdatedAt(LocalDateTime.parse(user.get("updated_at").toString()));

                                instructorDto.setUser(userDto);
                            } else {
                                instructorDto.setUser(null);

                                logger.warn("User data is null");
                            }

                            courseDto.setInstructor(instructorDto);
                        } else {
                            courseDto.setInstructor(null);

                            logger.warn("Instructor data is null");
                        }

                        Map<String, Object> category = (Map<String, Object>) course.get("category");

                        if (category != null) {
                            logger.info("Category data: {}", category);

                            CategoryDto categoryDto = new CategoryDto();

                            categoryDto.setId(Long.valueOf(category.get("id").toString()));
                            categoryDto.setName(category.get("name").toString());
                            categoryDto.setSlug(category.get("slug").toString());
                            categoryDto.setParentId(category.get("parent_id") != null ? Long.valueOf(category.get("parent_id").toString()) : null);
                            categoryDto.setIcon(category.get("icon") != null ? category.get("icon").toString() : null);
                            categoryDto.setIconUrl(category.get("icon_url") != null ? category.get("icon_url").toString() : null);
                            categoryDto.setCreatedAt(LocalDateTime.parse(category.get("created_at").toString()));
                            categoryDto.setUpdatedAt(LocalDateTime.parse(category.get("updated_at").toString()));

                            courseDto.setCategory(categoryDto);
                        } else {
                            courseDto.setCategory(null);

                            logger.warn("Category data is null");
                        }

                        return courseDto;
                    } else {
                        logger.warn("Course data is null");
                    }
                } else {
                    logger.warn("Response data is null");
                }
            } else {
                logger.warn("Feign failed: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            logger.warn("Failed to get course by id: {}", courseId);

            throw new RuntimeException("Failed to parse error response", e);
        }
    }
}
