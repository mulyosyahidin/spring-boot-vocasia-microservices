package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.mapper.InstructorBalanceHistoryMapper;
import com.vocasia.finance.mapper.InstructorBalanceMapper;
import com.vocasia.finance.service.IInstructorBalanceHistoryService;
import com.vocasia.finance.service.IInstructorBalanceService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor/balance")
public class InstructorBalanceController {

    private final Logger logger = LoggerFactory.getLogger(InstructorBalanceController.class);

    private final IInstructorBalanceService instructorBalanceService;
    private final IInstructorBalanceHistoryService instructorBalanceHistoryService;

    public InstructorBalanceController(IInstructorBalanceService iInstructorBalanceService, IInstructorBalanceHistoryService iInstructorBalanceHistoryService) {
        this.instructorBalanceService = iInstructorBalanceService;
        this.instructorBalanceHistoryService = iInstructorBalanceHistoryService;
    }

    @GetMapping("/data")
    public ResponseEntity<ResponseDto> getData(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                               @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorBalanceController.getData called");

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);

        if (instructorBalance == null) {
            Map<String, Object> response = new HashMap<>();

            Map<String, Object> emptyInstructorBalance = new HashMap<>();

            emptyInstructorBalance.put("current_balance", 0.0);
            emptyInstructorBalance.put("total_income", 0.0);
            emptyInstructorBalance.put("total_pending_withdrawal", 0.00);
            emptyInstructorBalance.put("total_withdrawn", 0.00);

            Map<String, Object> emptyHistoriesData = new HashMap<>();
            Map<String, Object> emptyPagination = new HashMap<>();

            emptyPagination.put("total_page", 0);
            emptyPagination.put("per_page", 10);
            emptyPagination.put("current_page", page);
            emptyPagination.put("total_items", 0);

            emptyHistoriesData.put("data", Collections.emptyList());
            emptyHistoriesData.put("pagination", emptyPagination);

            response.put("instructor_balance", emptyInstructorBalance);
            response.put("histories", emptyHistoriesData);

            return ResponseEntity
                    .status(HttpStatus.SC_OK)
                    .body(new ResponseDto(true, "Data balance instruktur belum tersedia", response, null));
        }

        Map<String, Object> response = new HashMap<>();

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("id")
        ));
        Page<InstructorBalanceHistory> instructorBalanceHistories = instructorBalanceHistoryService.findByInstructorId(instructorId, paging);

        Map<String, Object> historiesData = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", instructorBalanceHistories.getTotalPages());
        pagination.put("per_page", instructorBalanceHistories.getSize());
        pagination.put("current_page", instructorBalanceHistories.getNumber() + 1);
        pagination.put("total_items", instructorBalanceHistories.getTotalElements());

        historiesData.put("data", instructorBalanceHistories.getContent().stream().map(InstructorBalanceHistoryMapper::mapToDto));
        historiesData.put("pagination", pagination);

        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(instructorBalance));
        response.put("histories", historiesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data balance instruktur", response, null));
    }

    @GetMapping("/data/{historyId}")
    public ResponseEntity<ResponseDto> getDataByHistoryId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                          @PathVariable Long historyId) {
        logger.info("InstructorBalanceController.getDataByHistoryId called");

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);
        InstructorBalanceHistory instructorBalanceHistory = instructorBalanceHistoryService.findById(historyId);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(instructorBalance));
        response.put("history", InstructorBalanceHistoryMapper.mapToDto(instructorBalanceHistory));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data balance instruktur", response, null));
    }

}
