package com.vocasia.finance.controller;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.instructor.InstructorDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.entity.InstructorBalanceHistory;
import com.vocasia.finance.entity.WithdrawalProcess;
import com.vocasia.finance.entity.WithdrawalRequest;
import com.vocasia.finance.exception.CustomFeignException;
import com.vocasia.finance.mapper.WithdrawalProcessMapper;
import com.vocasia.finance.mapper.WithdrawalRequestMapper;
import com.vocasia.finance.request.NewInstructorBalanceHistoryRequest;
import com.vocasia.finance.request.WithdrawalProcessRequest;
import com.vocasia.finance.service.*;
import com.vocasia.finance.types.WithdrawalRequestStatus;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/withdrawal")
public class AdminWithdrawalController {

    private final Logger logger = LoggerFactory.getLogger(AdminWithdrawalController.class);

    private final IWithdrawalRequestService withdrawalRequestService;
    private final IInstructorService instructorService;
    private final IWithdrawalProcessService withdrawalProcessService;
    private final IInstructorBalanceService instructorBalanceService;
    private final IInstructorBalanceHistoryService instructorBalanceHistoryService;

    public AdminWithdrawalController(IWithdrawalRequestService iWithdrawalRequestService, IInstructorService iInstructorService,
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
                                                               @RequestParam(value = "status", required = false, defaultValue = "all") String status) {
        logger.info("AdminWithdrawalController.getAllWithdrawalRequest called");
        logger.debug("$status: {}", status);

        List<WithdrawalRequest> withdrawalRequests = switch (status) {
            case "pending" -> withdrawalRequestService.findAllByStatus(WithdrawalRequestStatus.PENDING.toString());
            case "paid" -> withdrawalRequestService.findAllByStatus(WithdrawalRequestStatus.PAID.toString());
            case "rejected" -> withdrawalRequestService.findAllByStatus(WithdrawalRequestStatus.REJECTED.toString());
            default -> withdrawalRequestService.findAll();
        };

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
        List<Map<String, Object>> withdrawalRequestsData = new ArrayList<>();

        for (WithdrawalRequest withdrawalRequest : withdrawalRequests) {
            Map<String, Object> withdrawalRequestData = new HashMap<>();

            withdrawalRequestData.put("request", WithdrawalRequestMapper.mapToDto(withdrawalRequest));

            try {
                InstructorDto getInstructorById = instructorService.findById(withdrawalRequest.getInstructorId(), correlationId);

                withdrawalRequestData.put("instructor", getInstructorById);
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

            withdrawalRequestsData.add(withdrawalRequestData);
        }

        response.put("overview", withdrawalOverviewData);
        response.put("requests", withdrawalRequestsData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan semua data request withdrawal", response, null));
    }

    @GetMapping("/request/{id}")
    public ResponseEntity<ResponseDto> getWithdrawalRequestById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                @PathVariable("id") Long id) {
        logger.info("AdminWithdrawalController.getWithdrawalRequestById called");

        WithdrawalRequest withdrawalRequest = withdrawalRequestService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("request", WithdrawalRequestMapper.mapToDto(withdrawalRequest));

        if (withdrawalRequest.getStatus().equals("PAID")) {
            WithdrawalProcess withdrawalProcess = withdrawalProcessService.findByWithdrawalRequestId(id);

            response.put("process", WithdrawalProcessMapper.mapToDto(withdrawalProcess));
        }

        try {
            InstructorDto getInstructorById = instructorService.findById(withdrawalRequest.getInstructorId(), correlationId);

            response.put("instructor", getInstructorById);
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
                .body(new ResponseDto(true, "Berhasil mendapatkan semua data request withdrawal", response, null));
    }

    @PostMapping("/request/{id}/process")
    public ResponseEntity<ResponseDto> processWithdrawal(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                         @PathVariable("id") Long id,
                                                         @RequestParam(value = "proof_document") @Valid MultipartFile proofDocument,
                                                         @RequestParam(value = "amount") Double amount,
                                                         @RequestParam(value = "note") String note) {
        logger.info("AdminWithdrawalController.processWithdrawal called");

        WithdrawalRequest withdrawalRequest = withdrawalRequestService.findById(id);
        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(withdrawalRequest.getInstructorId());

        withdrawalRequest.setStatus(WithdrawalRequestStatus.PAID.toString());
        withdrawalRequest.setProcessedAt(LocalDateTime.now());
        withdrawalRequest.setRemarks("Request processed successfully");
        withdrawalRequestService.update(withdrawalRequest);

        WithdrawalProcessRequest withdrawalProcessRequest = new WithdrawalProcessRequest();
        withdrawalProcessRequest.setProofDocument(proofDocument);
        withdrawalProcessRequest.setAmount(amount);
        withdrawalProcessRequest.setNote(note);

        Map<String, Object> response = new HashMap<>();

        try {
            NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest = getNewInstructorBalanceHistoryRequest(withdrawalRequest);

            WithdrawalProcess withdrawalProcess = withdrawalProcessService.save(id, withdrawalProcessRequest);
            InstructorBalanceHistory instructorBalanceHistory = instructorBalanceHistoryService.save(instructorBalance, newInstructorBalanceHistoryRequest);

            instructorBalanceService.updateBalance(newInstructorBalanceHistoryRequest, instructorBalance, instructorBalanceHistory);

            response.put("withdrawal_process", WithdrawalProcessMapper.mapToDto(withdrawalProcess));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Gagal mengupload file bukti prosess", null, e.getMessage()));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memproses withdrawal", response, null));
    }

    private static NewInstructorBalanceHistoryRequest getNewInstructorBalanceHistoryRequest(WithdrawalRequest withdrawalRequest) {
        NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest = new NewInstructorBalanceHistoryRequest();

        newInstructorBalanceHistoryRequest.setInstructorId(withdrawalRequest.getInstructorId());
        newInstructorBalanceHistoryRequest.setType("withdrawal");
        newInstructorBalanceHistoryRequest.setAmount(withdrawalRequest.getAmount());
        newInstructorBalanceHistoryRequest.setPlatformFee(0.0);
        newInstructorBalanceHistoryRequest.setReferenceId(withdrawalRequest.getId());
        newInstructorBalanceHistoryRequest.setReferenceType("withdrawal");
        newInstructorBalanceHistoryRequest.setTransactionStatus("success");
        newInstructorBalanceHistoryRequest.setRemarks("Withdrawal request processed");

        return newInstructorBalanceHistoryRequest;
    }

}
