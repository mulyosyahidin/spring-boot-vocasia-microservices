package com.vocasia.course.mapper;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.dto.data.CategoryDto;
import com.vocasia.course.dto.data.CourseDto;
import com.vocasia.course.dto.feign.InstructorDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Course;
import com.vocasia.course.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private static AwsConfigProperties awsConfigProperties;
    private static IInstructorService iInstructorService;

    @Autowired
    public CourseMapper(AwsConfigProperties awsConfigProperties, IInstructorService iInstructorService, ApplicationContext applicationContext) {
        CourseMapper.awsConfigProperties = awsConfigProperties;
        CourseMapper.iInstructorService = iInstructorService;
    }

    public static CourseDto mapToDto(Course course) {
        CourseDto courseDto = new CourseDto();

        courseDto.setId(course.getId());
        courseDto.setInstructorId(course.getInstructorId());

        Category category = course.getCategory();
        if (category != null) {
            courseDto.setCategory(CategoryMapper.mapToDto(category));
        }

        courseDto.setTitle(course.getTitle());
        courseDto.setSlug(course.getSlug());
        courseDto.setTotalDuration(course.getTotalDuration());
        courseDto.setLevel(course.getLevel());
        courseDto.setLanguage(course.getLanguage());
        courseDto.setDescription(course.getDescription());
        courseDto.setShortDescription(course.getShortDescription());
        courseDto.setFeaturedPicture(course.getFeaturedPicture());
        courseDto.setPrice(course.getPrice());
        courseDto.setIsFree(course.getIsFree());
        courseDto.setIsDiscount(course.getIsDiscount());
        courseDto.setDiscount(course.getDiscount());
        courseDto.setStatus(course.getStatus());
        courseDto.setCreatedAt(course.getCreatedAt());
        courseDto.setUpdatedAt(course.getUpdatedAt());
        courseDto.setDeletedAt(course.getDeletedAt());

        if (course.getFeaturedPicture() != null) {
            String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", awsConfigProperties.getS3().getBucket(), awsConfigProperties.getS3().getRegion());
            String featuredPictureUrl =bucketUrl + course.getFeaturedPicture();

            courseDto.setFeaturedPictureUrl(featuredPictureUrl);
        }

        try {
            InstructorDto instructorDto = iInstructorService.getInstructorById(course.getInstructorId());

            courseDto.setInstructor(instructorDto);
        } catch (Exception e) {
            e.printStackTrace();

            courseDto.setInstructor(null);
        }

        return courseDto;
    }

    public static Course mapToEntity(CourseDto courseDto) {
        Course course = new Course();

        // Mapping fields
        course.setId(courseDto.getId());
        course.setInstructorId(courseDto.getInstructorId());

        CategoryDto categoryDto = courseDto.getCategory();
        if (categoryDto != null) {
            course.setCategory(CategoryMapper.mapToEntity(categoryDto));
        }

        course.setTitle(courseDto.getTitle());
        course.setSlug(courseDto.getSlug());
        course.setTotalDuration(courseDto.getTotalDuration());
        course.setLevel(courseDto.getLevel());
        course.setLanguage(courseDto.getLanguage());
        course.setDescription(courseDto.getDescription());
        course.setShortDescription(courseDto.getShortDescription());
        course.setFeaturedPicture(courseDto.getFeaturedPicture());
        course.setPrice(courseDto.getPrice());
        course.setIsFree(courseDto.getIsFree());
        course.setIsDiscount(courseDto.getIsDiscount());
        course.setDiscount(courseDto.getDiscount());
        course.setStatus(courseDto.getStatus());
        course.setCreatedAt(courseDto.getCreatedAt());
        course.setUpdatedAt(courseDto.getUpdatedAt());
        course.setDeletedAt(courseDto.getDeletedAt());

        return course;
    }
}
