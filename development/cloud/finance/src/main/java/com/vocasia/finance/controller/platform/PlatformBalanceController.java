package com.vocasia.finance.controller.platform;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.mapper.PlatformBalanceHistoryMapper;
import com.vocasia.finance.mapper.PlatformBalanceMapper;
import com.vocasia.finance.request.NewPlatformBalanceHistoryRequest;
import com.vocasia.finance.service.IPlatformBalanceHistoryService;
import com.vocasia.finance.service.IPlatformBalanceService;
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
@RequestMapping("/api/platform-balance")
public class PlatformBalanceController {

    private final Logger logger = LoggerFactory.getLogger(PlatformBalanceController.class);

    private final IPlatformBalanceService platformBalanceService;
    private final IPlatformBalanceHistoryService platformBalanceHistoryService;

    public PlatformBalanceController(IPlatformBalanceService iPlatformBalanceService, IPlatformBalanceHistoryService iPlatformBalanceHistoryService) {
        this.platformBalanceService = iPlatformBalanceService;
        this.platformBalanceHistoryService = iPlatformBalanceHistoryService;
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto> createPlatformBalanceHistory(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                    @Valid @RequestBody NewPlatformBalanceHistoryRequest request) {
        logger.info("PlatformBalanceController.createPlatformBalanceHistory called");

        PlatformBalance platformBalance;

        boolean isPlatformHasBalanceRecord = platformBalanceService.isPlatformHasBalanceRecord();
        if (isPlatformHasBalanceRecord) {
            platformBalance = platformBalanceService.findPlatformBalance();
        }
        else {
            PlatformBalance newPlatformBalance = getPlatformBalance();
            platformBalance = platformBalanceService.save(newPlatformBalance);
        }

        PlatformBalanceHistory platformBalanceHistory = platformBalanceHistoryService.save(platformBalance, request);
        PlatformBalance updatedPlatformBalance = platformBalanceService.updateBalance(request, platformBalance, platformBalanceHistory);

        List<PlatformBalanceHistory> platformBalanceHistories = platformBalanceHistoryService.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("platform_balance", PlatformBalanceMapper.mapToDto(updatedPlatformBalance));
        response.put("new_history", PlatformBalanceHistoryMapper.mapToDto(platformBalanceHistory));
        response.put("histories", platformBalanceHistories.stream().map(PlatformBalanceHistoryMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data balance platform", response, null));
    }

    private static PlatformBalance getPlatformBalance() {
        PlatformBalance newPlatformBalance = new PlatformBalance();

        newPlatformBalance.setCurrentBalance(0.0);
        newPlatformBalance.setTotalIncome(0.0);
        newPlatformBalance.setTotalPendingWithdrawal(0.0);
        newPlatformBalance.setTotalWithdrawn(0.0);
        newPlatformBalance.setLastHistoryId(0L);

        return newPlatformBalance;
    }

}
