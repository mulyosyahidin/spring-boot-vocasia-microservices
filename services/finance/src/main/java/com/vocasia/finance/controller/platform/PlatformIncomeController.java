package com.vocasia.finance.controller.platform;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.PlatformIncome;
import com.vocasia.finance.mapper.PlatformIncomeMapper;
import com.vocasia.finance.request.NewPlatformIncomeRequest;
import com.vocasia.finance.service.IPlatformIncomeService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/platform-income")
public class PlatformIncomeController {

    private final Logger logger = LoggerFactory.getLogger(PlatformIncomeController.class);

    private final IPlatformIncomeService platformIncomeService;

    public PlatformIncomeController(IPlatformIncomeService iPlatformIncomeService) {
        this.platformIncomeService = iPlatformIncomeService;
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto> createPlatformIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                            @Valid @RequestBody NewPlatformIncomeRequest newPlatformIncomeRequest) {
        logger.info("PlatformIncomeController.createPlatformIncome called");

        PlatformIncome savedPlatformIncome = platformIncomeService.save(newPlatformIncomeRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("platform_income", PlatformIncomeMapper.mapToDto(savedPlatformIncome));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data pendapatan platform", response, null));
    }

}
