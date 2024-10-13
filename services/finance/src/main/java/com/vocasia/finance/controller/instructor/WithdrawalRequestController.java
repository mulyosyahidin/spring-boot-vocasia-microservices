package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.WithdrawalRequest;
import com.vocasia.finance.exception.ResourceNotFoundException;
import com.vocasia.finance.mapper.InstructorBalanceMapper;
import com.vocasia.finance.mapper.WithdrawalRequestMapper;
import com.vocasia.finance.request.WithdrawalRequestRequest;
import com.vocasia.finance.service.IInstructorBalanceService;
import com.vocasia.finance.service.IWithdrawalRequestService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/withdrawal")
public class WithdrawalRequestController {

    private final Logger logger = LoggerFactory.getLogger(WithdrawalRequestController.class);

    private final IWithdrawalRequestService withdrawalRequestService;
    private final IInstructorBalanceService instructorBalanceService;

    public WithdrawalRequestController(IWithdrawalRequestService iWithdrawalRequestService, IInstructorBalanceService iInstructorBalanceService) {
        this.withdrawalRequestService = iWithdrawalRequestService;
        this.instructorBalanceService = iInstructorBalanceService;
    }

    @GetMapping("/history")
    public ResponseEntity<ResponseDto> history(@RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("WithdrawalRequestController.history called");

        List<WithdrawalRequest> withdrawalRequests = withdrawalRequestService.findByInstructorId(instructorId);

        Map<String, Object> response = new HashMap<>();
        response.put("histories", withdrawalRequests.stream().map(WithdrawalRequestMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil data riwayat withdrawal", response, null));
    }

    @PostMapping("/request")
    public ResponseEntity<ResponseDto> request(@RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                               @Valid @RequestBody WithdrawalRequestRequest withdrawalRequest) {
        logger.info("WithdrawalRequestController.request called");

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);

        Double requestAmount = withdrawalRequest.getAmount();
        Double currentTotalPendingWithdrawal = instructorBalance.getTotalPendingWithdrawal() == null ? 0.00 : instructorBalance.getTotalPendingWithdrawal();
        Double currentBalance = instructorBalance.getCurrentBalance() - currentTotalPendingWithdrawal;

        if (requestAmount > currentBalance) {
            return ResponseEntity
                    .status(HttpStatus.SC_BAD_REQUEST)
                    .body(new ResponseDto(false, "Jumlah penarikan melebihi saldo tersedia", null, null));
        }

        Double currentPendingWithdrawal = instructorBalance.getTotalPendingWithdrawal();
        if (currentPendingWithdrawal == null) {
            currentPendingWithdrawal = 0.00;
        }

        Double newCurrentPendingWithdrawal = currentPendingWithdrawal + requestAmount;

        WithdrawalRequest savedWithdrawalRequest = withdrawalRequestService.save(instructorId, withdrawalRequest);
        InstructorBalance updatedInstructorBalancePendingWithdrawal = instructorBalanceService.updateTotalPendingWithdrawal(instructorBalance, newCurrentPendingWithdrawal);

        Map<String, Object> response = new HashMap<>();
        response.put("withdrawal_request", WithdrawalRequestMapper.mapToDto(savedWithdrawalRequest));
        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(updatedInstructorBalancePendingWithdrawal));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data request penarikan", response, null));
    }

}
