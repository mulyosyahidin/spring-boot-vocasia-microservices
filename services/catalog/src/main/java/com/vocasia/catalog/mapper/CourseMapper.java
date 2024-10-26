package com.vocasia.catalog.mapper;

import com.vocasia.catalog.dto.data.CourseDto;
import com.vocasia.catalog.entity.Course;

import java.util.stream.Collectors;

public class CourseMapper {

    public static CourseDto mapToDto(Course course) {
        CourseDto courseDto = new CourseDto();

        courseDto.setId(course.getId());
        courseDto.setInstructorId(course.getInstructorId());
        courseDto.setCategoryId(course.getCategoryId());
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

        if (course.getCategory() != null) {
            CourseDto.Category categoryDto = getCategory(course);
            courseDto.setCategory(categoryDto);
        }

        // Mapping Instructor
        if (course.getInstructor() != null) {
            CourseDto.Instructor instructorDto = getInstructor(course);

            courseDto.setInstructor(instructorDto);
        }

        // Mapping Chapters and Lessons
        if (course.getChapters() != null) {
            courseDto.setChapters(
                    course.getChapters().stream().map(chapter -> {
                        CourseDto.Chapter chapterDto = new CourseDto.Chapter();
                        chapterDto.setId(chapter.getId());
                        chapterDto.setTitle(chapter.getTitle());
                        chapterDto.setTotalDuration(chapter.getTotalDuration());

                        // Mapping Lessons within Chapters
                        if (chapter.getLessons() != null) {
                            chapterDto.setLessons(
                                    chapter.getLessons().stream().map(lesson -> {
                                        CourseDto.Chapter.Lesson lessonDto = new CourseDto.Chapter.Lesson();
                                        lessonDto.setId(lesson.getId());
                                        lessonDto.setTitle(lesson.getTitle());
                                        lessonDto.setType(lesson.getType());
                                        lessonDto.setNeedPreviousLesson(lesson.getNeedPreviousLesson());
                                        lessonDto.setIsFree(lesson.getIsFree());
                                        lessonDto.setContentVideoDuration(lesson.getContentVideoDuration());
                                        lessonDto.setContentVideoUrl(lesson.getContentVideoUrl());
                                        lessonDto.setContentText(lesson.getContentText());
                                        lessonDto.setAttachmentType(lesson.getAttachmentType());
                                        lessonDto.setAttachmentFileUrl(lesson.getAttachmentFileUrl());
                                        lessonDto.setAttachmentFileName(lesson.getAttachmentFileName());
                                        lessonDto.setAttachmentLink(lesson.getAttachmentLink());
                                        lessonDto.setAttachmentLinkName(lesson.getAttachmentLinkName());
                                        return lessonDto;
                                    }).collect(Collectors.toList())
                            );
                        }
                        return chapterDto;
                    }).collect(Collectors.toList())
            );
        }

        return courseDto;
    }

    private static CourseDto.Instructor getInstructor(Course course) {
        CourseDto.Instructor instructorDto = new CourseDto.Instructor();

        instructorDto.setId(course.getInstructor().getId());
        instructorDto.setUserId(course.getInstructor().getUserId());
        instructorDto.setStatus(course.getInstructor().getStatus());
        instructorDto.setPhoneNumber(course.getInstructor().getPhoneNumber());
        instructorDto.setSummary(course.getInstructor().getSummary());

        // Mapping User within Instructor
        if (course.getInstructor().getUser() != null) {
            CourseDto.Instructor.User userDto = new CourseDto.Instructor.User();

            userDto.setId(course.getInstructor().getUser().getId());
            userDto.setEmail(course.getInstructor().getUser().getEmail());
            userDto.setUsername(course.getInstructor().getUser().getUsername());
            userDto.setName(course.getInstructor().getUser().getName());
            userDto.setProfilePicture(course.getInstructor().getUser().getProfilePicture());

            instructorDto.setUser(userDto);
        }

        return instructorDto;
    }

    private static CourseDto.Category getCategory(Course course) {
        CourseDto.Category categoryDto = new CourseDto.Category();

        categoryDto.setId(course.getCategory().getId());
        categoryDto.setType(course.getCategory().getType());
        categoryDto.setName(course.getCategory().getName());
        categoryDto.setSlug(course.getCategory().getSlug());
        categoryDto.setIcon(course.getCategory().getIcon());
        categoryDto.setCreatedAt(course.getCategory().getCreatedAt());
        categoryDto.setUpdatedAt(course.getCategory().getUpdatedAt());

        return categoryDto;
    }

}
