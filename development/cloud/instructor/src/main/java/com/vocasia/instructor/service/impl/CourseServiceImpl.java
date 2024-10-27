package com.vocasia.instructor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.course.CategoryDto;
import com.vocasia.instructor.dto.client.course.CourseDto;
import com.vocasia.instructor.dto.client.course.InstructorOverviewDto;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.service.ICourseService;
import com.vocasia.instructor.service.client.CourseFeignClient;
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

    private CourseFeignClient courseFeignClient;

    @Override
    public CourseDto findById(Long courseId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> courseFeignClientFindByIdResponseEntity = courseFeignClient.findById(correlationId, courseId);
            ResponseDto responseBody = courseFeignClientFindByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            Map<String, Object> course = data != null ? (Map<String, Object>) data.get("course") : null;
            Map<String, Object> category = data != null ? (Map<String, Object>) data.get("category") : null;

            logger.debug("CourseServiceImpl.findById() $course:: {}", course);
            logger.debug("CourseServiceImpl.findById() $category:: {}", category);

            CourseDto courseDto = new CourseDto();
            CategoryDto categoryDto = new CategoryDto();

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

    @Override
    public InstructorOverviewDto getInstructorOverview(Long instructorId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> courseFeignClientInstructorCourseOverviewResponseEntity = courseFeignClient.instructorCourseOverview(correlationId, instructorId);
            ResponseDto responseBody = courseFeignClientInstructorCourseOverviewResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            Map<String, Object> overview = data != null ? (Map<String, Object>) data.get("overview") : null;

            logger.debug("CourseServiceImpl.getInstructorOverview() $overview:: {}", overview);

            InstructorOverviewDto instructorOverviewDto = new InstructorOverviewDto();

            if (overview != null) {
                instructorOverviewDto.setDraft(Integer.parseInt(overview.get("draft").toString()));
                instructorOverviewDto.setPublished(Integer.parseInt(overview.get("published").toString()));
                instructorOverviewDto.setTotal(Integer.parseInt(overview.get("total").toString()));
            }

            return instructorOverviewDto;
        }
        catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
