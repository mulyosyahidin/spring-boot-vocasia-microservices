package com.vocasia.qna.controller.student;

import com.vocasia.qna.dto.ResponseDto;
import com.vocasia.qna.dto.client.authentication.UserDto;
import com.vocasia.qna.entity.Qna;
import com.vocasia.qna.exception.CustomFeignException;
import com.vocasia.qna.mapper.QnaMapper;
import com.vocasia.qna.request.AskQuestionRequest;
import com.vocasia.qna.service.IAuthenticationService;
import com.vocasia.qna.service.IQnaService;
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
@RequestMapping("/api/student")
public class StudentQnAController {

    private final Logger logger = LoggerFactory.getLogger(StudentQnAController.class);

    private final IAuthenticationService authenticationService;
    private final IQnaService qnaService;

    public StudentQnAController(IAuthenticationService iAuthenticationService, IQnaService iQnaService) {
        this.authenticationService = iAuthenticationService;
        this.qnaService = iQnaService;
    }

    @GetMapping("/is-i-ask-this-lesson/{lessonId}")
    public ResponseEntity<ResponseDto> isIAskThisLesson(@RequestHeader("X-USER-ID") Long userId,
                                                        @PathVariable("lessonId") Long lessonId) {
        logger.info("StudentQnAController.isIAskThisLesson()");

        boolean isIAskThisLesson = qnaService.existsByUserIdAndLessonId(userId, lessonId);

        Map<String, Object> response = new HashMap<>();

        if (isIAskThisLesson) {
            List<Qna> qnas = qnaService.findAllByUserIdAndLessonId(userId, lessonId);

            response.put("questions", qnas.stream().map(QnaMapper::mapToDto));
        }

        response.put("is_i_answer_this_lesson", isIAskThisLesson);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data", response, null));
    }

    @GetMapping("/questions/{courseId}/{lessonId}")
    public ResponseEntity<ResponseDto> getQuestions(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @PathVariable("courseId") Long courseId,
                                                    @PathVariable("lessonId") Long lessonId,
                                                    @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentQnAController.getQuestions()");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Order.desc("createdAt")));

        Page<Qna> qnas = qnaService.findAllByLessonId(lessonId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> qnasData;

        try {
            qnasData = qnas.getContent().stream().map(qna -> {
                Map<String, Object> qnaData = new HashMap<>();

                UserDto userDto = authenticationService.findUserById(qna.getUserId(), correlationId);

                qnaData.put("qna", QnaMapper.mapToDto(qna));
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
        response.put("questions", qnasData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data", response, null));
    }

    @PostMapping("/questions/{courseId}/{lessonId}")
    public ResponseEntity<ResponseDto> saveQuestion(@RequestHeader("X-USER-ID") Long userId,
                                                    @PathVariable("courseId") Long courseId,
                                                    @PathVariable("lessonId") Long lessonId,
                                                    @Valid @RequestBody AskQuestionRequest request) {
        logger.info("StudentQnAController.saveQuestion()");

        request.setCourseId(courseId);
        request.setLessonId(lessonId);

        Qna qna = qnaService.save(userId, request);

        Map<String, Object> response = new HashMap<>();
        response.put("question", QnaMapper.mapToDto(qna));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan pertanyaan", response, null));
    }

}
