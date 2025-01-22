package com.vocasia.catalog.service.impl;

import com.vocasia.catalog.entity.Course;
import com.vocasia.catalog.repository.CourseRepository;
import com.vocasia.catalog.request.course.StoreCourseRequest;
import com.vocasia.catalog.service.ICourseService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    @Override
    public Course save(StoreCourseRequest storeCourseRequest) {
        Course course = new Course();

        course.setId(storeCourseRequest.getId());
        course.setInstructorId(storeCourseRequest.getInstructorId());
        course.setCategoryId(storeCourseRequest.getCategoryId());
        course.setTitle(storeCourseRequest.getTitle());
        course.setSlug(storeCourseRequest.getSlug());
        course.setTotalDuration(storeCourseRequest.getTotalDuration());
        course.setLevel(storeCourseRequest.getLevel());
        course.setLanguage(storeCourseRequest.getLanguage());
        course.setDescription(storeCourseRequest.getDescription());
        course.setShortDescription(storeCourseRequest.getShortDescription());
        course.setFeaturedPicture(storeCourseRequest.getFeaturedPicture());
        course.setPrice(storeCourseRequest.getPrice());
        course.setIsFree(storeCourseRequest.getIsFree());
        course.setIsDiscount(storeCourseRequest.getIsDiscount());
        course.setDiscount(storeCourseRequest.getDiscount());
        course.setStatus(storeCourseRequest.getStatus());
        course.setCreatedAt(storeCourseRequest.getCreatedAt());
        course.setUpdatedAt(storeCourseRequest.getUpdatedAt());
        course.setDeletedAt(storeCourseRequest.getDeletedAt());

        Course.Category category = getCategory(storeCourseRequest);
        course.setCategory(category);

        if (storeCourseRequest.getInstructor().getId() != null) {
            Course.Instructor instructor = getInstructor(storeCourseRequest);
            course.setInstructor(instructor);
        } else {
            course.setInstructor(null);
        }

        List<Course.Chapter> chapters = getChapters(storeCourseRequest);
        course.setChapters(chapters);

        return courseRepository.save(course);
    }

    private List<Course.Chapter> getChapters(StoreCourseRequest storeCourseRequest) {
        List<Course.Chapter> chapters = new ArrayList<>();
        List<StoreCourseRequest.Chapter> storeChapters = storeCourseRequest.getChapters();

        for (StoreCourseRequest.Chapter storeChapter : storeChapters) {
            Course.Chapter chapter = new Course.Chapter();
            chapter.setId(storeChapter.getId());
            chapter.setTitle(storeChapter.getTitle());
            chapter.setTotalDuration(storeChapter.getTotalDuration());

            List<Course.Chapter.Lesson> lessons = new ArrayList<>();
            List<StoreCourseRequest.Chapter.Lesson> chapterLessons = storeChapter.getLessons();

            for (StoreCourseRequest.Chapter.Lesson chapterLesson : chapterLessons) {
                Course.Chapter.Lesson lesson = getLesson(chapterLesson);

                lessons.add(lesson);
            }

            chapter.setLessons(lessons);

            chapters.add(chapter);
        }

        return chapters;
    }

    private static Course.Chapter.Lesson getLesson(StoreCourseRequest.Chapter.Lesson chapterLesson) {
        Course.Chapter.Lesson lesson = new Course.Chapter.Lesson();

        lesson.setId(chapterLesson.getId());
        lesson.setTitle(chapterLesson.getTitle());
        lesson.setType(chapterLesson.getType());
        lesson.setNeedPreviousLesson(chapterLesson.getNeedPreviousLesson());
        lesson.setIsFree(chapterLesson.getIsFree());
        lesson.setContentVideoDuration(chapterLesson.getContentVideoDuration());
        lesson.setContentVideoUrl(chapterLesson.getContentVideoUrl());
        lesson.setContentText(chapterLesson.getContentText());
        lesson.setAttachmentFileUrl(chapterLesson.getAttachmentFileUrl());
        lesson.setAttachmentFileName(chapterLesson.getAttachmentFileName());
        lesson.setAttachmentLink(chapterLesson.getAttachmentLink());
        lesson.setAttachmentLinkName(chapterLesson.getAttachmentLinkName());

        return lesson;
    }

    private static Course.Instructor getInstructor(StoreCourseRequest storeCourseRequest) {
        Course.Instructor instructor = new Course.Instructor();

        instructor.setId(storeCourseRequest.getInstructor().getId());
        instructor.setUserId(storeCourseRequest.getInstructor().getUserId());
        instructor.setStatus(storeCourseRequest.getInstructor().getStatus());
        instructor.setSummary(storeCourseRequest.getInstructor().getSummary());
        instructor.setPhoneNumber(storeCourseRequest.getInstructor().getPhoneNumber());

        Course.Instructor.User user = new Course.Instructor.User();
        user.setId(storeCourseRequest.getInstructor().getUser().getId());
        user.setEmail(storeCourseRequest.getInstructor().getUser().getEmail());
        user.setUsername(storeCourseRequest.getInstructor().getUser().getUsername());
        user.setName(storeCourseRequest.getInstructor().getUser().getName());
        user.setProfilePicture(storeCourseRequest.getInstructor().getUser().getProfilePicture());

        instructor.setUser(user);

        return instructor;
    }

    private static Course.Category getCategory(StoreCourseRequest storeCourseRequest) {
        Course.Category category = new Course.Category();

        category.setId(storeCourseRequest.getCategoryId());
        category.setType(storeCourseRequest.getCategory().getType());
        category.setName(storeCourseRequest.getCategory().getName());
        category.setSlug(storeCourseRequest.getCategory().getSlug());
        category.setIcon(storeCourseRequest.getCategory().getIcon());
        category.setCreatedAt(storeCourseRequest.getCategory().getCreatedAt());
        category.setUpdatedAt(storeCourseRequest.getCategory().getUpdatedAt());

        return category;
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findLatestCourses() {
        return courseRepository.findTop10ByOrderByCreatedAtDesc();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

}
