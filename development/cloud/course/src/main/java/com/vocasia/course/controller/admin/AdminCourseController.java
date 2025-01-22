package com.vocasia.course.controller.admin;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.client.instructor.InstructorDto;
import com.vocasia.course.entity.Category;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.mapper.CategoryMapper;
import com.vocasia.course.mapper.ChapterMapper;
import com.vocasia.course.mapper.CourseMapper;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.request.client.catalog.course.StoreCourseRequest;
import com.vocasia.course.service.*;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminCourseController {

    private final Logger logger = LoggerFactory.getLogger(AdminCourseController.class);

    private final ICategoryService categoryService;
    private final ICourseService courseService;
    private final ICatalogService catalogService;
    private final IInstructorService instructorService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public AdminCourseController(ICategoryService iCategoryService, ICourseService iCourseService, ICatalogService iCatalogService, IInstructorService iInstructorService,
                                 IChapterService iChapterService, ILessonService iLessonService) {
        this.categoryService = iCategoryService;
        this.courseService = iCourseService;
        this.catalogService = iCatalogService;
        this.instructorService = iInstructorService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getOverview() {
        logger.info("AdminCourseController.getOverview called");

        Map<String, Object> response = new HashMap<>();
        response.put("total_courses", courseService.count());
        response.put("total_draft_courses", courseService.countByStatus("draft"));
        response.put("total_published_courses", courseService.countByStatus("published"));
        response.put("total_paid_courses", courseService.countByIsFree(false));
        response.put("total_free_courses", courseService.countByIsFree(true));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data ringkasan kursus", response, null));
    }

    @GetMapping("/courses")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestParam(defaultValue = "1") int page) {
        logger.info("AdminCourseController.getAllCourses called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Course> courses = courseService.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> coursesData;

        try {
            coursesData = courses.getContent().stream().map(course -> {
                Map<String, Object> courseData = new HashMap<>();

                InstructorDto instructor = instructorService.findById(course.getInstructorId(), correlationId);

                courseData.put("course", CourseMapper.mapToDto(course));
                courseData.put("instructor", instructor);

                return courseData;
            }).toList();
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        pagination.put("total_page", courses.getTotalPages());
        pagination.put("per_page", courses.getSize());
        pagination.put("current_page", courses.getNumber() + 1);
        pagination.put("total_items", courses.getTotalElements());

        response.put("data", coursesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<ResponseDto> getAllCourses(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable("courseId") Long courseId) {
        logger.info("AdminCourseController.getCourseById called");

        Map<String, Object> response = new HashMap<>();

        Course course = courseService.findById(courseId);
        Category category = categoryService.findById(course.getCategoryId());

        List<Chapter> chapters = chapterService.findAllByCourseId(course);

        List<Map<String, Object>> chaptersData = new ArrayList<>();

        for (Chapter chapter : chapters) {
            Map<String, Object> chapterData = new HashMap<>();

            chapterData.put("chapter", ChapterMapper.mapToDto(chapter));
            chapterData.put("lessons", lessonService.findAllByChapterId(chapter.getId()).stream().map(LessonMapper::mapToDto));

            chaptersData.add(chapterData);
        }

        response.put("course", CourseMapper.mapToDto(course));
        response.put("category", CategoryMapper.mapToDto(category));
        response.put("chapters", chaptersData);

        try {
            InstructorDto instructor = instructorService.findById(course.getInstructorId(), correlationId);

            response.put("instructor", instructor);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data kursus", response, null));
    }

    @PostMapping("/courses/sync")
    public ResponseEntity<ResponseDto> syncCourses(@RequestHeader("vocasia-correlation-id") String correlationId) {
        logger.info("AdminCourseController.syncCourses called");

        List<Course> publishedCourses = courseService.findAllByStatus("published");

        try {
            logger.info("Starting to sync courses");
            int totalSyncedCourses = 0;

            for (Course publishedCourse : publishedCourses) {
                logger.info("Syncing course with id: " + publishedCourse.getId());

                InstructorDto instructor = instructorService.findById(publishedCourse.getInstructorId(), correlationId);

                StoreCourseRequest storeCourseRequest = new StoreCourseRequest();

                storeCourseRequest.setId(publishedCourse.getId());
                storeCourseRequest.setInstructorId(publishedCourse.getInstructorId());
                storeCourseRequest.setCategoryId(publishedCourse.getCategoryId());
                storeCourseRequest.setTitle(publishedCourse.getTitle());
                storeCourseRequest.setSlug(publishedCourse.getSlug());
                storeCourseRequest.setTotalDuration(publishedCourse.getTotalDuration());
                storeCourseRequest.setLevel(publishedCourse.getLevel());
                storeCourseRequest.setLanguage(publishedCourse.getLanguage());
                storeCourseRequest.setDescription(publishedCourse.getDescription());
                storeCourseRequest.setShortDescription(publishedCourse.getShortDescription());
                storeCourseRequest.setFeaturedPicture(publishedCourse.getFeaturedPicture());
                storeCourseRequest.setPrice(publishedCourse.getPrice());
                storeCourseRequest.setIsFree(publishedCourse.getIsFree());
                storeCourseRequest.setIsDiscount(publishedCourse.getIsDiscount());
                storeCourseRequest.setDiscount(publishedCourse.getDiscount());
                storeCourseRequest.setStatus(publishedCourse.getStatus());
                storeCourseRequest.setCreatedAt(publishedCourse.getCreatedAt());
                storeCourseRequest.setUpdatedAt(publishedCourse.getUpdatedAt());
                storeCourseRequest.setDeletedAt(publishedCourse.getDeletedAt());

                Category category = categoryService.findById(publishedCourse.getCategoryId());

                StoreCourseRequest.Category storeCourseCategory = getCategory(category);
                storeCourseRequest.setCategory(storeCourseCategory);

                if (instructor.getId() != null) {
                    StoreCourseRequest.Instructor storeCourseInstructor = getInstructor(instructor);
                    storeCourseRequest.setInstructor(storeCourseInstructor);
                } else {
                    storeCourseRequest.setInstructor(null);
                }

                List<StoreCourseRequest.Chapter> storeCourseChapters = new ArrayList<>();
                List<Chapter> chapters = chapterService.findAllByCourseId(publishedCourse);

                for (Chapter chapter : chapters) {
                    StoreCourseRequest.Chapter storeCourseChapter = new StoreCourseRequest.Chapter();

                    storeCourseChapter.setId(chapter.getId());
                    storeCourseChapter.setTitle(chapter.getTitle());
                    storeCourseChapter.setTotalDuration(chapter.getTotalDuration());

                    List<StoreCourseRequest.Chapter.Lesson> storeCourseLessons = new ArrayList<>();
                    List<Lesson> lessons = lessonService.findAllByChapterId(chapter.getId());

                    for (Lesson lesson : lessons) {
                        StoreCourseRequest.Chapter.Lesson storeCourseLesson = getLesson(lesson);

                        storeCourseLessons.add(storeCourseLesson);
                    }

                    storeCourseChapter.setLessons(storeCourseLessons);

                    storeCourseChapters.add(storeCourseChapter);
                }

                storeCourseRequest.setChapters(storeCourseChapters);

                catalogService.saveCourse(storeCourseRequest, correlationId);

                logger.info("Course with id: " + publishedCourse.getId() + " has been synced");
                totalSyncedCourses++;
            }

            logger.info("Total courses synced: " + totalSyncedCourses);
        } catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil melakukan sinkronisasi data kursus", null, null));
    }

    private static StoreCourseRequest.Chapter.Lesson getLesson(Lesson lesson) {
        StoreCourseRequest.Chapter.Lesson storeCourseLesson = new StoreCourseRequest.Chapter.Lesson();

        storeCourseLesson.setId(lesson.getId());
        storeCourseLesson.setTitle(lesson.getTitle());
        storeCourseLesson.setType(lesson.getType());
        storeCourseLesson.setNeedPreviousLesson(lesson.getNeedPreviousLesson());
        storeCourseLesson.setIsFree(lesson.getIsFree());
        storeCourseLesson.setContentVideoDuration(lesson.getContentVideoDuration());
        storeCourseLesson.setContentVideoUrl(lesson.getContentVideoUrl());
        storeCourseLesson.setContentText(lesson.getContentText());
        storeCourseLesson.setAttachmentFileUrl(lesson.getAttachmentFileUrl());
        storeCourseLesson.setAttachmentFileName(lesson.getAttachmentFileName());
        storeCourseLesson.setAttachmentLink(lesson.getAttachmentLink());
        storeCourseLesson.setAttachmentLinkName(lesson.getAttachmentLinkName());

        return storeCourseLesson;
    }

    private static StoreCourseRequest.Category getCategory(Category category) {
        StoreCourseRequest.Category storeCourseCategory = new StoreCourseRequest.Category();

        storeCourseCategory.setId(category.getId());
        storeCourseCategory.setType(category.getType());
        storeCourseCategory.setParentId(category.getParentId());
        storeCourseCategory.setName(category.getName());
        storeCourseCategory.setSlug(category.getSlug());
        storeCourseCategory.setIcon(category.getIcon());
        storeCourseCategory.setCreatedAt(category.getCreatedAt());
        storeCourseCategory.setUpdatedAt(category.getUpdatedAt());

        return storeCourseCategory;
    }

    private static StoreCourseRequest.Instructor getInstructor(InstructorDto instructor) {
        StoreCourseRequest.Instructor storeCourseInstructor = new StoreCourseRequest.Instructor();

        storeCourseInstructor.setId(instructor.getId());
        storeCourseInstructor.setUserId(Long.valueOf(instructor.getUserId()));
        storeCourseInstructor.setStatus(instructor.getStatus());
        storeCourseInstructor.setSummary(instructor.getSummary());
        storeCourseInstructor.setPhoneNumber(instructor.getPhoneNumber());

        StoreCourseRequest.Instructor.User storeCourseUser = new StoreCourseRequest.Instructor.User();
        storeCourseUser.setId(Long.valueOf(instructor.getUserId()));
        storeCourseUser.setEmail(instructor.getUser().getEmail());
        storeCourseUser.setUsername(instructor.getUser().getUsername());
        storeCourseUser.setName(instructor.getUser().getName());
        storeCourseUser.setProfilePicture(instructor.getUser().getProfilePicture());

        storeCourseInstructor.setUser(storeCourseUser);

        return storeCourseInstructor;
    }

}
