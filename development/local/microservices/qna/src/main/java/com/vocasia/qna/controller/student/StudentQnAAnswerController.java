package com.vocasia.qna.controller.student;

import com.vocasia.qna.dto.ResponseDto;
import com.vocasia.qna.dto.client.authentication.UserDto;
import com.vocasia.qna.entity.Qna;
import com.vocasia.qna.entity.QnaAnswer;
import com.vocasia.qna.exception.CustomFeignException;
import com.vocasia.qna.mapper.QnaAnswerMapper;
import com.vocasia.qna.mapper.QnaMapper;
import com.vocasia.qna.request.PostQnaAnswerRequest;
import com.vocasia.qna.service.IAuthenticationService;
import com.vocasia.qna.service.IQnaAnswerService;
import com.vocasia.qna.service.IQnaService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
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
@RequestMapping("/api/student")
public class StudentQnAAnswerController {

    private final Logger logger = LoggerFactory.getLogger(StudentQnAAnswerController.class);

    private final IAuthenticationService authenticationService;
    private final IQnaService qnaService;
    private final IQnaAnswerService qnaAnswerService;

    public StudentQnAAnswerController(IAuthenticationService iAuthenticationService, IQnaService iQnaService, IQnaAnswerService iQnaAnswerService) {
        this.authenticationService = iAuthenticationService;
        this.qnaService = iQnaService;
        this.qnaAnswerService = iQnaAnswerService;
    }

    @GetMapping("/questions/{lessonId}/qna/{qnaId}")
    public ResponseEntity<ResponseDto> getQnaAnswers(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @PathVariable("lessonId") Long lessonId,
                                                     @PathVariable("qnaId") Long qnaId,
                                                     @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentQnAAnswerController.getQnaAnswers called");

        Qna qna = qnaService.findById(qnaId);

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit);

        Page<QnaAnswer> qnaAnswers = qnaAnswerService.findAllByQnaId(qnaId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> qnaAnswersData;

        try {
            qnaAnswersData = qnaAnswers.getContent().stream().map(qnaAnswer -> {
                Map<String, Object> qnaAnswerData = new HashMap<>();

                UserDto userDto = authenticationService.findUserById(qnaAnswer.getUserId(), correlationId);

                qnaAnswerData.put("qna", QnaAnswerMapper.mapToDto(qnaAnswer));
                qnaAnswerData.put("user", userDto);

                return qnaAnswerData;
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

        pagination.put("total_page", qnaAnswers.getTotalPages());
        pagination.put("per_page", qnaAnswers.getSize());
        pagination.put("current_page", qnaAnswers.getNumber() + 1);
        pagination.put("total_items", qnaAnswers.getTotalElements());

        response.put("qna", QnaMapper.mapToDto(qna));
        response.put("answers", qnaAnswersData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data jawaban QnA", response, null));
    }

    @PostMapping("/questions/{lessonId}/qna/{qnaId}")
    public ResponseEntity<ResponseDto> postAnswer(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                  @RequestHeader("X-USER-ID") Long userId,
                                                  @PathVariable("lessonId") Long lessonId,
                                                  @PathVariable("qnaId") Long qnaId,
                                                  @Valid @RequestBody PostQnaAnswerRequest request) {
        logger.info("StudentQnAAnswerController.postAnswer called");

        request.setUserId(userId);

        Qna qna = qnaService.findById(qnaId);
        QnaAnswer qnaAnswer = qnaAnswerService.save(qnaId, request);

        Map<String, Object> response = new HashMap<>();

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambahkan jawaban baru", null, null));
    }

}
