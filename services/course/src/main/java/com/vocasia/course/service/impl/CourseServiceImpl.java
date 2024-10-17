package com.vocasia.course.service.impl;

import com.vocasia.course.config.AwsConfigProperties;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.exception.ResourceNotFoundException;
import com.vocasia.course.packages.aws.service.IAwsService;
import com.vocasia.course.repository.CategoryRepository;
import com.vocasia.course.repository.ChapterRepository;
import com.vocasia.course.repository.CourseRepository;
import com.vocasia.course.request.CreateNewCourseRequest;
import com.vocasia.course.request.UpdateCourseRequest;
import com.vocasia.course.request.UpdateCourseThumbnailRequest;
import com.vocasia.course.service.ICourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final ChapterRepository chapterRepository;

    @Override
    public Course save(Long instructorId, CreateNewCourseRequest createNewCourseRequest) {
        Course course = new Course();

        course.setInstructorId(instructorId);
        course.setCategoryId(createNewCourseRequest.getCategoryId());
        course.setTitle(createNewCourseRequest.getTitle());
        course.setSlug(createNewCourseRequest.getSlug());
        course.setShortDescription(createNewCourseRequest.getShortDescription());
        course.setLevel(createNewCourseRequest.getLevel());
        course.setLanguage(createNewCourseRequest.getLanguage());
        course.setDescription(createNewCourseRequest.getDescription());
        course.setTotalDuration(createNewCourseRequest.getTotalDuration());

        if (createNewCourseRequest.getPrice() == null) {
            course.setIsFree(true);
        } else {
            course.setPrice(createNewCourseRequest.getPrice());
            course.setIsFree(createNewCourseRequest.getPrice() < 1);
        }

        if (createNewCourseRequest.getDiscount() > 0) {
            course.setDiscount(createNewCourseRequest.getDiscount());
            course.setIsDiscount(true);
        } else {
            course.setDiscount(0.0);
            course.setIsDiscount(false);
        }

        return courseRepository.save(course);
    }

    @Override
    public Course updateCourseThumbnail(Course course, UpdateCourseThumbnailRequest updateCourseThumbnailRequest) throws IOException {
        String bucketName = awsConfigProperties.getS3().getBucket();
        MultipartFile picture = updateCourseThumbnailRequest.getPicture();

        String timeInMillis = String.valueOf(System.currentTimeMillis());
        String fileName = timeInMillis + "-" + StringUtils.cleanPath(Objects.requireNonNull(picture.getOriginalFilename()));

        String contentType = picture.getContentType();
        long fileSize = picture.getSize();
        InputStream inputStream = picture.getInputStream();

        awsService.uploadFile(bucketName, fileName, fileSize, contentType, inputStream);
        course.setFeaturedPicture(fileName);

        return courseRepository.save(course);
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Data tidak ditemukan"));
    }

    @Override
    public Course update(Course course, UpdateCourseRequest updateCourseRequest) {
        course.setTitle(updateCourseRequest.getTitle());
        course.setCategoryId(updateCourseRequest.getCategoryId());
        course.setShortDescription(updateCourseRequest.getShortDescription());
        course.setLevel(updateCourseRequest.getLevel());
        course.setLanguage(updateCourseRequest.getLanguage());
        course.setDescription(updateCourseRequest.getDescription());
        course.setTotalDuration(updateCourseRequest.getTotalDuration());

        if (updateCourseRequest.getPrice() != null) {
            course.setPrice(updateCourseRequest.getPrice());
            course.setIsFree(false);
        } else {
            course.setIsFree(true);
        }

        if (updateCourseRequest.getDiscount() > 0) {
            course.setDiscount(updateCourseRequest.getDiscount());
            course.setIsDiscount(true);
        } else {
            course.setDiscount(0.0);
            course.setIsDiscount(false);
        }

        return courseRepository.save(course);
    }

    @Override
    public Course publishCourseById(Course course) {
        course.setStatus("published");

        return courseRepository.save(course);
    }

    @Override
    public List<Course> getEditorsChoices() {
        return courseRepository.findByStatus("published");
    }

    @Override
    public Integer chapterCount(Long courseId) {
        return chapterRepository.countByCourseId(courseId);
    }

    @Override
    public Integer lessonCount(Long courseId) {
        List<Chapter> chapters = chapterRepository.findAllByCourseId(courseId);
        int count = 0;

        for (Chapter chapter : chapters) {
            count += chapter.getLessons().size();
        }

        return count;
    }

    @Override
    public Double rating(Long courseId) {
        return 0.0;
    }

    @Override
    public Integer ratingCount(Long courseId) {
        return 0;
    }

    @Override
    public Integer enrollmentCount(Long courseId) {
        return 0;
    }

    @Override
    public Page<Course> findAllByInstructorId(Long instructorId, Pageable paging) {
        return courseRepository.findAllByInstructorId(instructorId, paging);
    }

    @Override
    public Page<Course> findAllByInstructorIdAndStatus(Long instructorId, String status, Pageable paging) {
        return courseRepository.findAllByInstructorIdAndStatus(instructorId, status, paging);
    }

    @Override
    public Page<Course> findAllPublishedByInstructorId(Long instructorId, Pageable paging) {
        return courseRepository.findAllPublishedByInstructorId(instructorId, paging);
    }

    @Override
    public Page<Course> findAllDraftByInstructorId(Long instructorId, Pageable paging) {
        return courseRepository.findAllDraftByInstructorId(instructorId, paging);
    }

    @Override
    public int countByStatusAndInstructorId(String status, Long instructorId) {
        return courseRepository.countByStatusAndInstructorId(status, instructorId);
    }

}
