package com.vocasia.course.controller.instructor;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.entity.Chapter;
import com.vocasia.course.entity.Course;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.request.lesson.StoreLessonRequest;
import com.vocasia.course.request.lesson.UpdateLessonRequest;
import com.vocasia.course.service.IChapterService;
import com.vocasia.course.service.ICourseService;
import com.vocasia.course.service.ILessonService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor/courses/")
public class InstructorLessonController {

    private final Logger logger = LoggerFactory.getLogger(InstructorLessonController.class);

    private final ICourseService courseService;
    private final IChapterService chapterService;
    private final ILessonService lessonService;

    public InstructorLessonController(ICourseService iCourseService, IChapterService iChapterService, ILessonService iLessonService) {
        this.courseService = iCourseService;
        this.chapterService = iChapterService;
        this.lessonService = iLessonService;
    }

    @GetMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> getAllLessonByChapterId(@PathVariable Long courseId, @PathVariable Long chapterId) {
        logger.info("InstructorLessonController.getAllLessonByChapterId called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        List<Lesson> lessons = lessonService.findAllByChapterId(chapterId);

        Map<String, Object> response = new HashMap<>();
        response.put("lessons", lessons.stream().map(LessonMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @PostMapping("/{courseId}/chapters/{chapterId}/lessons")
    public ResponseEntity<ResponseDto> createLesson(@PathVariable Long courseId,
                                                    @PathVariable Long chapterId,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("type") String type,
                                                    @RequestParam(value = "need_previous_lesson", required = false) boolean needPreviousLesson,
                                                    @RequestParam(value = "is_free", required = false) boolean isFree,
                                                    @RequestParam(value = "content_video_duration", required = false) String contentVideoDuration,
                                                    @RequestParam(value = "content_video_url", required = false) String contentVideoUrl,
                                                    @RequestParam(value = "content_text", required = false) String contentText,
                                                    @RequestParam(value = "attachment_type", required = false) String attachmentType,
                                                    @RequestParam(value = "attachment_file_name", required = false) String attachmentFileName,
                                                    @RequestParam(value = "attachment_file", required = false) @Valid MultipartFile attachmentFile,
                                                    @RequestParam(value = "attachment_link", required = false) String attachmentLink,
                                                    @RequestParam(value = "attachment_link_name", required = false) String attachmentLinkName
    ) {
        logger.info("InstructorLessonController.createLesson called");

        StoreLessonRequest storeLessonRequest = new StoreLessonRequest();
        storeLessonRequest.setTitle(title);
        storeLessonRequest.setType(type);
        storeLessonRequest.setNeedPreviousLesson(needPreviousLesson);
        storeLessonRequest.setIsFree(isFree);
        storeLessonRequest.setContentVideoDuration(contentVideoDuration);
        storeLessonRequest.setContentVideoUrl(contentVideoUrl);
        storeLessonRequest.setContentText(contentText);
        storeLessonRequest.setAttachmentType(attachmentType);
        storeLessonRequest.setAttachmentFileName(attachmentFileName);
        storeLessonRequest.setAttachmentFile(attachmentFile);
        storeLessonRequest.setAttachmentLink(attachmentLink);
        storeLessonRequest.setAttachmentLinkName(attachmentLinkName);

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);

        Map<String, Object> response = new HashMap<>();

        try {
            Lesson lesson = lessonService.save(chapter, storeLessonRequest);
            response.put("lesson", LessonMapper.mapToDto(lesson));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.hc.core5.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambah data pelajaran", response, null));
    }

    @GetMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> getLessonById(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        logger.info("InstructorLessonController.getLessonById called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findById(lessonId);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @GetMapping("/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> getLessonById(@PathVariable Long lessonId) {
        logger.info("InstructorLessonController.getLessonById called");

        Lesson lesson = lessonService.findById(lessonId);

        Map<String, Object> response = new HashMap<>();
        response.put("lesson", LessonMapper.mapToDto(lesson));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mengambil data pelajaran", response, null));
    }

    @PutMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> updateLesson(@PathVariable Long courseId,
                                                    @PathVariable Long chapterId,
                                                    @PathVariable Long lessonId,
                                                    @RequestParam("title") String title,
                                                    @RequestParam("type") String type,
                                                    @RequestParam(value = "need_previous_lesson", required = false) boolean needPreviousLesson,
                                                    @RequestParam(value = "is_free", required = false) boolean isFree,
                                                    @RequestParam(value = "content_video_duration", required = false) String contentVideoDuration,
                                                    @RequestParam(value = "content_video_url", required = false) String contentVideoUrl,
                                                    @RequestParam(value = "content_text", required = false) String contentText,
                                                    @RequestParam(value = "attachment_type", required = false) String attachmentType,
                                                    @RequestParam(value = "attachment_file_name", required = false) String attachmentFileName,
                                                    @RequestParam(value = "attachment_file", required = false) @Valid MultipartFile attachmentFile,
                                                    @RequestParam(value = "attachment_link", required = false) String attachmentLink,
                                                    @RequestParam(value = "attachment_link_name", required = false) String attachmentLinkName,
                                                    @RequestParam(value = "remove_attachment", required = false) boolean removeAttachment
    ) {
        logger.info("InstructorLessonController.updateLesson called");

        UpdateLessonRequest updateLessonRequest = new UpdateLessonRequest();
        updateLessonRequest.setTitle(title);
        updateLessonRequest.setType(type);
        updateLessonRequest.setNeedPreviousLesson(needPreviousLesson);
        updateLessonRequest.setIsFree(isFree);
        updateLessonRequest.setContentVideoDuration(contentVideoDuration);
        updateLessonRequest.setContentVideoUrl(contentVideoUrl);
        updateLessonRequest.setContentText(contentText);
        updateLessonRequest.setAttachmentType(attachmentType);
        updateLessonRequest.setAttachmentFileName(attachmentFileName);
        updateLessonRequest.setAttachmentFile(attachmentFile);
        updateLessonRequest.setAttachmentLink(attachmentLink);
        updateLessonRequest.setAttachmentLinkName(attachmentLinkName);
        updateLessonRequest.setRemoveAttachment(removeAttachment);

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findByChapterIdAndId(chapterId, lessonId);

        Map<String, Object> response = new HashMap<>();

        try {
            Lesson updatedLesson = lessonService.update(lesson, updateLessonRequest);
            response.put("lesson", LessonMapper.mapToDto(updatedLesson));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(org.apache.hc.core5.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload icon", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui data pelajaran", response, null));
    }

    @DeleteMapping("/{courseId}/chapters/{chapterId}/lessons/{lessonId}")
    public ResponseEntity<ResponseDto> deleteLesson(@PathVariable Long courseId, @PathVariable Long chapterId, @PathVariable Long lessonId) {
        logger.info("InstructorLessonController.deleteLesson called");

        Course course = courseService.findById(courseId);
        Chapter chapter = chapterService.findById(chapterId);
        Lesson lesson = lessonService.findByChapterIdAndId(chapterId, lessonId);

        lessonService.deleteById(lesson);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menghapus data pelajaran", null, null));
    }

}
