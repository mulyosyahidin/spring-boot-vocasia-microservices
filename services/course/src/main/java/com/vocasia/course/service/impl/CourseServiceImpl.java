package com.vocasia.course.service.impl;

import com.amazonaws.services.macie2.model.AwsService;
import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.packages.aws.service.IAwsService;
import com.vocasia.course.repository.CategoryRepository;
import com.vocasia.course.repository.CourseRepository;
import com.vocasia.course.request.CreateChapterRequest;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final AwsConfigProperties awsConfigProperties;

    private final IAwsService awsService;

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Course store(CreateNewCourseRequest createNewCourseRequest) {
        Course course = new Course();

        course.setInstructorId(createNewCourseRequest.getInstructorId());

        course.setTitle(createNewCourseRequest.getTitle());
        course.setSlug(createNewCourseRequest.getSlug());
        course.setShortDescription(createNewCourseRequest.getShortDescription());
        course.setLevel(createNewCourseRequest.getLevel());
        course.setLanguage(createNewCourseRequest.getLanguage());
        course.setDescription(createNewCourseRequest.getDescription());
        course.setStatus("draft");

        if (createNewCourseRequest.getPrice() != null) {
            course.setPrice(createNewCourseRequest.getPrice());
            course.setIsFree(false);
        }
        else {
            course.setIsFree(true);
        }

        if (createNewCourseRequest.getDiscount() > 0) {
            course.setDiscount(createNewCourseRequest.getDiscount());
            course.setIsDiscount(true);
        }
        else {
            course.setDiscount(0.0);
            course.setIsDiscount(false);
        }

        if (createNewCourseRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(Long.parseLong(createNewCourseRequest.getCategoryId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

            course.setCategory(category);
        }

        return courseRepository.save(course);
    }

    @Override
    public Course updateThumbnail(Course course, UpdateCourseThumbnailRequest updateCourseThumbnailRequest) throws IOException {
        String bucketName = awsConfigProperties.getS3().getBucket();
        MultipartFile picture = updateCourseThumbnailRequest.getPicture();

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));

        String contentType = picture.getContentType();
        long fileSize = picture.getSize();
        InputStream inputStream = picture.getInputStream();

        awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);
        course.setFeaturedPicture(fileName);

        return courseRepository.save(course);
    }

    @Override
    public Course show(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    @Override
    public List<Course> getDraftCourses() {
        return courseRepository.findByStatus("draft");
    }

    @Override
    public List<Course> getDraftCoursesByInstructorId(Long instructorId) {
        return courseRepository.findByInstructorIdAndStatus(instructorId, "draft");
    }

    @Override
    public Course updateCourse(Long courseId, UpdateCourseRequest updateCourseRequest) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        course.setTitle(updateCourseRequest.getTitle());
        course.setShortDescription(updateCourseRequest.getShortDescription());
        course.setLevel(updateCourseRequest.getLevel());
        course.setLanguage(updateCourseRequest.getLanguage());
        course.setDescription(updateCourseRequest.getDescription());
        course.setTotalDuration(updateCourseRequest.getTotalDuration());

        if (updateCourseRequest.getPrice() != null) {
            course.setPrice(updateCourseRequest.getPrice());
            course.setIsFree(false);
        }
        else {
            course.setIsFree(true);
        }

        if (updateCourseRequest.getDiscount() > 0) {
            course.setDiscount(updateCourseRequest.getDiscount());
            course.setIsDiscount(true);
        }
        else {
            course.setDiscount(0.0);
            course.setIsDiscount(false);
        }

        if (updateCourseRequest.getCategoryId() != null) {
            Category category = categoryRepository.findById(Long.parseLong(updateCourseRequest.getCategoryId()))
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

            course.setCategory(category);
        }

        return courseRepository.save(course);
    }

}
