package com.vocasia.course.controller.qna.instructor;

import com.vocasia.course.dto.ResponseDto;
import com.vocasia.course.dto.client.authentication.UserDto;
import com.vocasia.course.dto.data.LessonDto;
import com.vocasia.course.entity.Lesson;
import com.vocasia.course.entity.Qna;
import com.vocasia.course.entity.QnaAnswer;
import com.vocasia.course.exception.CustomFeignException;
import com.vocasia.course.mapper.LessonMapper;
import com.vocasia.course.mapper.QnaAnswerMapper;
import com.vocasia.course.mapper.QnaMapper;
import com.vocasia.course.request.qna.PostQnaAnswerRequest;
import com.vocasia.course.service.*;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/qna/instructor")
public class CourseQnaController {

    private final Logger logger = LoggerFactory.getLogger(CourseQnaController.class);

    private final IAuthenticationService authenticationService;
    private final ICourseService courseService;
    private final ILessonService lessonService;
    private final IQnaService qnaService;
    private final IQnaAnswerService qnaAnswerService;

    public CourseQnaController(IAuthenticationService iAuthenticationService, ICourseService iCourseService, ILessonService iLessonService, IQnaService iQnaService, IQnaAnswerService iQnaAnswerService) {
        this.authenticationService = iAuthenticationService;
        this.courseService = iCourseService;
        this.lessonService = iLessonService;
        this.qnaService = iQnaService;
        this.qnaAnswerService = iQnaAnswerService;
    }

    @GetMapping("/courses/{courseId}/questions")
    public ResponseEntity<ResponseDto> getCourseQuestions(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @PathVariable("courseId") Long courseId,
                                                          @RequestParam(defaultValue = "1") int page) {
        logger.info("CourseQnaController.getCourseQuestions called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createdAt")));

        Page<Qna> qnas = qnaService.findAllByCourseId(courseId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> qnasData;

        try {
            qnasData = qnas.getContent().stream().map(qna -> {
                Map<String, Object> qnaData = new HashMap<>();

                Lesson lesson = lessonService.findById(qna.getLessonId());
                UserDto userDto = authenticationService.findUserById(qna.getUserId(), correlationId);

                qnaData.put("qna", QnaMapper.mapToDto(qna));
                qnaData.put("lesson", LessonMapper.mapToDto(lesson));
                qnaData.put("user", userDto);

                return qnaData;
            }).toList();
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

        pagination.put("total_page", qnas.getTotalPages());
        pagination.put("per_page", qnas.getSize());
        pagination.put("current_page", qnas.getNumber() + 1);
        pagination.put("total_items", qnas.getTotalElements());

        response.put("pagination", pagination);
        response.put("data", qnasData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data QnA", response, null));
    }

    @GetMapping("/courses/{courseId}/questions/{qnaId}")
    public ResponseEntity<ResponseDto> getCourseQuestionById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @PathVariable("courseId") Long courseId,
                                                             @PathVariable("qnaId") Long qnaId,
                                                             @RequestParam(defaultValue = "1") int page) {
        logger.info("CourseQnaController.getCourseQuestionById called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit);

        Page<QnaAnswer> qnaAnswers = qnaAnswerService.findAllByQnaId(qnaId, paging);
        Qna qna = qnaService.findById(qnaId);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> answers = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> qnaAnswersData;

        response.put("question", QnaMapper.mapToDto(qna));

        try {
            Lesson lesson = lessonService.findById(qna.getLessonId());
            UserDto userDto = authenticationService.findUserById(qna.getUserId(), correlationId);

            qnaAnswersData = qnaAnswers.getContent().stream().map(qnaAnswer -> {
                Map<String, Object> qnaAnswerData = new HashMap<>();

                UserDto userDtoAnswer = authenticationService.findUserById(qnaAnswer.getUserId(), correlationId);

                qnaAnswerData.put("answer", QnaAnswerMapper.mapToDto(qnaAnswer));
                qnaAnswerData.put("user", userDtoAnswer);

                return qnaAnswerData;
            }).toList();

            response.put("user", userDto);
            response.put("lesson", LessonMapper.mapToDto(lesson));
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

        pagination.put("total_page", qnaAnswers.getTotalPages());
        pagination.put("per_page", qnaAnswers.getSize());
        pagination.put("current_page", qnaAnswers.getNumber() + 1);
        pagination.put("total_items", qnaAnswers.getTotalElements());

        answers.put("data", qnaAnswersData);
        answers.put("pagination", pagination);

        response.put("answers", answers);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data QnA", response, null));
    }

    @PostMapping("/courses/{courseId}/questions/{qnaId}")
    public ResponseEntity<ResponseDto> postQnaAnswer(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader("X-USER-ID") Long userId,
                                                     @PathVariable("courseId") Long courseId,
                                                     @PathVariable("qnaId") Long qnaId,
                                                     @Valid @RequestBody PostQnaAnswerRequest request) {
        logger.info("CourseQnaController.postQnaAnswer called");

        request.setIsInstructor(true);
        request.setUserId(userId);

        Qna qna = qnaService.findById(qnaId);
        QnaAnswer qnaAnswer = qnaAnswerService.save(qnaId, request);

        Map<String, Object> response = new HashMap<>();

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan jawaban baru", null, null));
    }

}
