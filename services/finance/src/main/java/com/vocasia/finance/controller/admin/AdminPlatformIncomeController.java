package com.vocasia.finance.controller.admin;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.course.CourseDto;
import com.vocasia.finance.entity.PlatformIncome;
import com.vocasia.finance.mapper.PlatformIncomeMapper;
import com.vocasia.finance.service.ICourseService;
import com.vocasia.finance.service.IPlatformIncomeService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminPlatformIncomeController {

    private final Logger logger = LoggerFactory.getLogger(AdminPlatformIncomeController.class);

    private final IPlatformIncomeService platformIncomeService;
    private final ICourseService courseService;

    public AdminPlatformIncomeController(IPlatformIncomeService iPlatformIncomeService, ICourseService iCourseService) {
        this.platformIncomeService = iPlatformIncomeService;
        this.courseService = iCourseService;
    }

    @GetMapping("/platform-income/{orderId}")
    public ResponseEntity<ResponseDto> findPlatformIncomeByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                     @PathVariable("orderId") Long orderId) {
        logger.info("PlatformIncomeController.findPlatformIncomeByOrderId called");

        List<PlatformIncome> platformIncomes = platformIncomeService.findAllByOrderId(orderId);
        List<Map<String, Object>> platformIncomesData = platformIncomes.stream().map(platformIncome -> {
            Map<String, Object> platformIncomeData = new HashMap<>();

            CourseDto course = courseService.findById(platformIncome.getCourseId(), correlationId);

            platformIncomeData.put("platform_income", PlatformIncomeMapper.mapToDto(platformIncome));
            platformIncomeData.put("course", course);

            return platformIncomeData;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("platform_incomes", platformIncomesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data incomes platform", response, null));
    }
    
}
