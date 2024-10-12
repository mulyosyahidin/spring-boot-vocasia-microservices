package com.vocasia.finance.controller;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.order.OrderDto;
import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.exception.CustomFeignException;
import com.vocasia.finance.mapper.InstructorIncomeMapper;
import com.vocasia.finance.request.NewInstructorIncomeRequest;
import com.vocasia.finance.service.IInstructorIncomeService;
import com.vocasia.finance.service.IOrderService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor-income")
public class InstructorIncomeController {

    private final Logger logger = LoggerFactory.getLogger(InstructorIncomeController.class);

    private final IInstructorIncomeService instructorIncomeService;
    private final IOrderService orderService;

    public InstructorIncomeController(IInstructorIncomeService iInstructorIncomeService, IOrderService iOrderService) {
        this.instructorIncomeService = iInstructorIncomeService;
        this.orderService = iOrderService;
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto> createInstructorIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                            @Valid @RequestBody NewInstructorIncomeRequest newInstructorIncomeRequest) {
        logger.info("InstructorIncomeController.createInstructorIncome called");

        InstructorIncome savedInstructorIncome = instructorIncomeService.save(newInstructorIncomeRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_income", InstructorIncomeMapper.mapToDto(savedInstructorIncome));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data pendapatan instruktur", response, null));
    }

    @GetMapping("/course-income/{courseId}")
    public ResponseEntity<ResponseDto> getCourseIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                       @PathVariable Long courseId) {
        logger.info("InstructorIncomeController.getCourseIncome called");

        List<InstructorIncome> incomes = instructorIncomeService.findAllIncomeByCourseId(courseId);

        Map<String, Object> response = new HashMap<>();

        Integer totalTransaction = incomes.size();
        double totalIncome = incomes.stream().mapToDouble(InstructorIncome::getTotalInstructorIncome).sum();

        double totalPlatformIncome = incomes.stream().map(income -> {
            BigDecimal feeInPercent = income.getPlatformFeeInPercent();
            Double totalUserPayment = income.getTotalUserPayment();

            return totalUserPayment * (feeInPercent.doubleValue() / 100);
        }).mapToDouble(Double::doubleValue).sum();

        Map<String, Object> overview = new HashMap<>();

        overview.put("total_transaction", totalTransaction);
        overview.put("total_user_payment", totalIncome + totalPlatformIncome);
        overview.put("total_income", totalIncome);
        overview.put("total_platform_income", totalPlatformIncome);

        response.put("overview", overview);

        List<Map<String, Object>> incomesData = new ArrayList<>();

        for (InstructorIncome instructorIncome : incomes) {
            Map<String, Object> incomeData = new HashMap<>();

            incomeData.put("income", InstructorIncomeMapper.mapToDto(instructorIncome));

            try {
                OrderDto getInstructorProfileByUserId = orderService.findById(instructorIncome.getOrderId(), correlationId);

                incomeData.put("order", getInstructorProfileByUserId);
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

            incomesData.add(incomeData);
        }

        response.put("incomes", incomesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data pendapatan kursus", response, null));
    }

}
