package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.mapper.InstructorBalanceHistoryMapper;
import com.vocasia.finance.mapper.InstructorBalanceMapper;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;
import com.vocasia.finance.service.IInstructorBalanceHistoryService;
import com.vocasia.finance.service.IInstructorBalanceService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor/balance")
public class BalanceController {

    private final Logger logger = LoggerFactory.getLogger(BalanceController.class);

    private final IInstructorBalanceService instructorBalanceService;
    private final IInstructorBalanceHistoryService instructorBalanceHistoryService;

    public BalanceController(IInstructorBalanceService iInstructorBalanceService, IInstructorBalanceHistoryService iInstructorBalanceHistoryService) {
        this.instructorBalanceService = iInstructorBalanceService;
        this.instructorBalanceHistoryService = iInstructorBalanceHistoryService;
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto> createInstructorBalanceHistory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                      @Valid @RequestBody NewInstructorBalanceHistoryRequest request) {
        logger.info("BalanceController.createInstructorBalanceHistory called");

        InstructorBalance instructorBalance;

        boolean isInstructorHasBalanceRecord = instructorBalanceService.isInstructorHasBalanceRecord(request.getInstructorId());
        if (isInstructorHasBalanceRecord) {
            instructorBalance = instructorBalanceService.findByInstructorId(request.getInstructorId());
        }
        else {
            InstructorBalance newInstructorBalance = getInstructorBalance(request);
            instructorBalance = instructorBalanceService.save(newInstructorBalance);
        }

        InstructorBalanceHistory instructorBalanceHistory = instructorBalanceHistoryService.save(instructorBalance, request);
        InstructorBalance updatedInstructorBalance = instructorBalanceService.updateBalance(request, instructorBalance, instructorBalanceHistory);

        List<InstructorBalanceHistory> instructorBalanceHistories = instructorBalanceHistoryService.findByInstructorId(request.getInstructorId());

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(updatedInstructorBalance));
        response.put("new_history", InstructorBalanceHistoryMapper.mapToDto(instructorBalanceHistory));
        response.put("histories", instructorBalanceHistories.stream().map(InstructorBalanceHistoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data balance instruktur", response, null));
    }

    @GetMapping("/data")
    public ResponseEntity<ResponseDto> getData(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                               @RequestParam(defaultValue = "1") int page) {
        logger.info("BalanceController.getData called");

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);

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
        logger.info("BalanceController.getDataByHistoryId called");

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);
        InstructorBalanceHistory instructorBalanceHistory = instructorBalanceHistoryService.findById(historyId);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(instructorBalance));
        response.put("history", InstructorBalanceHistoryMapper.mapToDto(instructorBalanceHistory));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data balance instruktur", response, null));
    }

    private static InstructorBalance getInstructorBalance(NewInstructorBalanceHistoryRequest request) {
        InstructorBalance newInstructorBalance = new InstructorBalance();

        newInstructorBalance.setInstructorId(request.getInstructorId());
        newInstructorBalance.setCurrentBalance(0.0);
        newInstructorBalance.setTotalIncome(0.0);
        newInstructorBalance.setTotalPendingWithdrawal(0.0);
        newInstructorBalance.setTotalWithdrawn(0.0);
        newInstructorBalance.setTotalPlatformFee(0.0);
        newInstructorBalance.setLastHistoryId(0L);

        return newInstructorBalance;
    }

}
