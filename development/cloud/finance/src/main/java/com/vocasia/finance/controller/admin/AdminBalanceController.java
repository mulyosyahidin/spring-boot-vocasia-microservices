package com.vocasia.finance.controller.admin;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.PlatformBalance;
import com.vocasia.finance.entity.PlatformBalanceHistory;
import com.vocasia.finance.mapper.PlatformBalanceHistoryMapper;
import com.vocasia.finance.mapper.PlatformBalanceMapper;
import com.vocasia.finance.service.IPlatformBalanceHistoryService;
import com.vocasia.finance.service.IPlatformBalanceService;
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
@RequestMapping("/api/admin/balance")
public class AdminBalanceController {
    
    private final Logger logger = LoggerFactory.getLogger(AdminBalanceController.class);
    
    private final IPlatformBalanceService platformBalanceService;
    private final IPlatformBalanceHistoryService platformBalanceHistoryService;
    
    public AdminBalanceController(IPlatformBalanceService iPlatformBalanceService, IPlatformBalanceHistoryService iPlatformBalanceHistoryService) {
        this.platformBalanceService = iPlatformBalanceService;
        this.platformBalanceHistoryService = iPlatformBalanceHistoryService;
    }

    @GetMapping("/data")
    public ResponseEntity<ResponseDto> getData(@RequestHeader("vocasia-correlation-id") String correlationId,
                                               @RequestParam(defaultValue = "1") int page) {
        logger.info("PlatformBalanceController.getData called");

        PlatformBalance platformBalance = platformBalanceService.findPlatformBalance();

        if (platformBalance == null) {
            Map<String, Object> response = new HashMap<>();

            Map<String, Object> emptyPlatformBalance = new HashMap<>();

            emptyPlatformBalance.put("current_balance", 0.0);
            emptyPlatformBalance.put("total_income", 0.0);
            emptyPlatformBalance.put("total_pending_withdrawal", 0.00);
            emptyPlatformBalance.put("total_withdrawn", 0.00);

            Map<String, Object> emptyHistoriesData = new HashMap<>();
            Map<String, Object> emptyPagination = new HashMap<>();

            emptyPagination.put("total_page", 0);
            emptyPagination.put("per_page", 10);
            emptyPagination.put("current_page", page);
            emptyPagination.put("total_items", 0);

            emptyHistoriesData.put("data", Collections.emptyList());
            emptyHistoriesData.put("pagination", emptyPagination);

            response.put("platform_balance", emptyPlatformBalance);
            response.put("histories", emptyHistoriesData);

            return ResponseEntity
                    .status(HttpStatus.SC_OK)
                    .body(new ResponseDto(true, "Data balance platform belum tersedia", response, null));
        }

        Map<String, Object> response = new HashMap<>();

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("id")
        ));
        Page<PlatformBalanceHistory> platformBalanceHistories = platformBalanceHistoryService.findAll(paging);

        Map<String, Object> historiesData = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", platformBalanceHistories.getTotalPages());
        pagination.put("per_page", platformBalanceHistories.getSize());
        pagination.put("current_page", platformBalanceHistories.getNumber() + 1);
        pagination.put("total_items", platformBalanceHistories.getTotalElements());

        historiesData.put("data", platformBalanceHistories.getContent().stream().map(PlatformBalanceHistoryMapper::mapToDto));
        historiesData.put("pagination", pagination);

        response.put("platform_balance", PlatformBalanceMapper.mapToDto(platformBalance));
        response.put("histories", historiesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data balance platform", response, null));
    }
    
}
