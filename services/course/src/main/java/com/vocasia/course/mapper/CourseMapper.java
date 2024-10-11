package com.vocasia.course.mapper;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.dto.data.CourseDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Course;
import com.vocasia.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    private static AwsConfigProperties awsConfigProperties;
    private static ICourseService courseService;

    @Autowired
    public CourseMapper(AwsConfigProperties awsConfigProperties, ICourseService courseService) {
        CourseMapper.awsConfigProperties = awsConfigProperties;
        CourseMapper.courseService = courseService;
    }

    public static CourseDto mapToDto(Course course) {
        Category category = course.getCategory();

        CourseDto courseDto = new CourseDto();

        Integer chapterCount = courseService.chapterCount(course.getId());
        Integer lessonCount = courseService.lessonCount(course.getId());

        Double rating  = courseService.rating(course.getId());
        Integer ratingCount = courseService.ratingCount(course.getId());
        Integer enrollmentCount = courseService.enrollmentCount(course.getId());

        courseDto.setId(course.getId());
        courseDto.setCategoryId(category.getId());
        courseDto.setInstructorId(course.getInstructorId());

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
        courseDto.setChapterCount(chapterCount);
        courseDto.setLessonCount(lessonCount);
        courseDto.setRating(rating);
        courseDto.setRatingCount(ratingCount);
        courseDto.setEnrollmentCount(enrollmentCount);
        courseDto.setCreatedAt(course.getCreatedAt());
        courseDto.setUpdatedAt(course.getUpdatedAt());
        courseDto.setDeletedAt(course.getDeletedAt());

        if (course.getFeaturedPicture() != null) {
            String bucketUrl = String.format("https://%s.s3.%s.amazonaws.com/", awsConfigProperties.getS3().getBucket(), awsConfigProperties.getS3().getRegion());
            String featuredPictureUrl =bucketUrl + course.getFeaturedPicture();

            courseDto.setFeaturedPictureUrl(featuredPictureUrl);
        }

        return courseDto;
    }
    
}
