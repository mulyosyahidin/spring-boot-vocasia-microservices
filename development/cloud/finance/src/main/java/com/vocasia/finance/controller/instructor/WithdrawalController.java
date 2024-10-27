package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.mapper.InstructorBalanceMapper;
import com.vocasia.finance.mapper.WithdrawalRequestMapper;
import com.vocasia.finance.request.WithdrawalRequest;
import com.vocasia.finance.service.IInstructorBalanceService;
import com.vocasia.finance.service.IWithdrawalRequestService;
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
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class WithdrawalController {

    private final Logger logger = LoggerFactory.getLogger(WithdrawalController.class);

    private final IWithdrawalRequestService withdrawalRequestService;
    private final IInstructorBalanceService instructorBalanceService;

    public WithdrawalController(IWithdrawalRequestService iWithdrawalRequestService, IInstructorBalanceService iInstructorBalanceService) {
        this.withdrawalRequestService = iWithdrawalRequestService;
        this.instructorBalanceService = iInstructorBalanceService;
    }

    @GetMapping("/withdrawal")
    public ResponseEntity<ResponseDto> findAll(@RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                               @RequestParam(defaultValue = "1") int page) {
        logger.info("WithdrawalController.findAll called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<com.vocasia.finance.entity.WithdrawalRequest> withdrawals = withdrawalRequestService.findAllByInstructorId(instructorId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", withdrawals.getTotalPages());
        pagination.put("per_page", withdrawals.getSize());
        pagination.put("current_page", withdrawals.getNumber() + 1);
        pagination.put("total_items", withdrawals.getTotalElements());

        response.put("data", withdrawals.getContent().stream().map(WithdrawalRequestMapper::mapToDto));
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan data withdrawal", response, null));
    }

    @PostMapping("/withdrawal")
    public ResponseEntity<ResponseDto> createWithdrawal(@RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                        @Valid @RequestBody WithdrawalRequest withdrawalRequest) {
        logger.info("WithdrawalController.createWithdrawal called");

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

        com.vocasia.finance.entity.WithdrawalRequest savedWithdrawalRequest = withdrawalRequestService.save(instructorId, withdrawalRequest);
        InstructorBalance updatedInstructorBalancePendingWithdrawal = instructorBalanceService.updateTotalPendingWithdrawal(instructorBalance, newCurrentPendingWithdrawal);

        Map<String, Object> response = new HashMap<>();
        response.put("withdrawal_request", WithdrawalRequestMapper.mapToDto(savedWithdrawalRequest));
        response.put("instructor_balance", InstructorBalanceMapper.mapToDto(updatedInstructorBalancePendingWithdrawal));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil melakukan permintaan withdrawal", response, null));
    }

}
