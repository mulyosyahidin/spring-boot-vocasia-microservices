package com.vocasia.finance.controller.admin;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.WithdrawalRequest;
import com.vocasia.finance.mapper.WithdrawalRequestMapper;
import com.vocasia.finance.service.*;
import com.vocasia.finance.types.WithdrawalRequestStatus;
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
@RequestMapping("/api/admin/instructor/withdrawal")
public class InstructorWithdrawalRequestController {

    private final Logger logger = LoggerFactory.getLogger(InstructorWithdrawalRequestController.class);

    private final IWithdrawalRequestService withdrawalRequestService;
    private final IInstructorService instructorService;
    private final IWithdrawalProcessService withdrawalProcessService;
    private final IInstructorBalanceService instructorBalanceService;
    private final IInstructorBalanceHistoryService instructorBalanceHistoryService;

    public InstructorWithdrawalRequestController(IWithdrawalRequestService iWithdrawalRequestService, IInstructorService iInstructorService,
                                     IWithdrawalProcessService iWithdrawalProcessService, IInstructorBalanceService iInstructorBalanceService,
                                     IInstructorBalanceHistoryService iInstructorBalanceHistoryService) {
        this.withdrawalRequestService = iWithdrawalRequestService;
        this.instructorService = iInstructorService;
        this.withdrawalProcessService = iWithdrawalProcessService;
        this.instructorBalanceService = iInstructorBalanceService;
        this.instructorBalanceHistoryService = iInstructorBalanceHistoryService;
    }

    @GetMapping("/request")
    public ResponseEntity<ResponseDto> getAllWithdrawalRequest(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                               @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                               @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorWithdrawalRequestController.getAllWithdrawalRequest called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<WithdrawalRequest> withdrawalRequests = withdrawalRequestService.findAllByInstructorId(instructorId, paging);

        int totalPendingRequest = withdrawalRequestService.countByStatus(WithdrawalRequestStatus.PENDING.toString());
        Double totalPendingRequestAmount = withdrawalRequestService.sumAmountByStatus(WithdrawalRequestStatus.PENDING.toString());

        int totalPaidRequest = withdrawalRequestService.countByStatus(WithdrawalRequestStatus.PAID.toString());
        Double totalPaidRequestAmount = withdrawalRequestService.sumAmountByStatus(WithdrawalRequestStatus.PAID.toString());

        Map<String, Object> withdrawalOverviewData = new HashMap<>();
        withdrawalOverviewData.put("total_pending_request", totalPendingRequest);
        withdrawalOverviewData.put("total_pending_amount", totalPendingRequestAmount);
        withdrawalOverviewData.put("total_paid_request", totalPaidRequest);
        withdrawalOverviewData.put("total_paid_amount", totalPaidRequestAmount);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> withdrawalRequestsData = withdrawalRequests.getContent().stream().map(withdrawalRequest -> {
            Map<String, Object> withdrawalRequestData = new HashMap<>();

            withdrawalRequestData.put("request", WithdrawalRequestMapper.mapToDto(withdrawalRequest));

            return withdrawalRequestData;
        }).toList();

        pagination.put("total_page", withdrawalRequests.getTotalPages());
        pagination.put("per_page", withdrawalRequests.getSize());
        pagination.put("current_page", withdrawalRequests.getNumber() + 1);
        pagination.put("total_items", withdrawalRequests.getTotalElements());

        Map<String, Object> requestsData = new HashMap<>();
        requestsData.put("data", withdrawalRequestsData);
        requestsData.put("pagination", pagination);

        response.put("overview", withdrawalOverviewData);
        response.put("requests", requestsData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data request withdrawal instruktur", response, null));
    }

}
