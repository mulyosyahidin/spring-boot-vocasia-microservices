package com.vocasia.finance.controller.instructor;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InstructorIncomeController {

    private final Logger logger = LoggerFactory.getLogger(InstructorIncomeController.class);

    private final IInstructorIncomeService instructorIncomeService;
    private final IOrderService orderService;

    public InstructorIncomeController(IInstructorIncomeService iInstructorIncomeService, IOrderService iOrderService) {
        this.instructorIncomeService = iInstructorIncomeService;
        this.orderService = iOrderService;
    }

    @PostMapping("/instructor-income/store")
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

    @GetMapping("/instructor/courses/{courseId}/income")
    public ResponseEntity<ResponseDto> getCourseIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                       @PathVariable Long courseId,
                                                       @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorIncomeController.getCourseIncome called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<InstructorIncome> instructorIncomes = instructorIncomeService.findAllByCourseId(courseId, paging);
        List<InstructorIncome> allInstructorIncomes = instructorIncomeService.findAllByCourseId(courseId);

        Map<String, Object> response = new HashMap<>();

        Integer totalTransaction = allInstructorIncomes.size();
        double totalIncome = allInstructorIncomes.stream().mapToDouble(InstructorIncome::getTotalInstructorIncome).sum();

        double totalPlatformIncome = allInstructorIncomes.stream().map(income -> {
            BigDecimal feeInPercent = income.getPlatformFeeInPercent();
            Double totalUserPayment = income.getTotalUserPayment();

            return totalUserPayment * (feeInPercent.doubleValue() / 100);
        }).mapToDouble(Double::doubleValue).sum();

        Map<String, Object> overview = new HashMap<>();
        overview.put("total_transaction", totalTransaction);
        overview.put("total_user_payment", totalIncome + totalPlatformIncome);
        overview.put("total_income", totalIncome);
        overview.put("total_platform_income", totalPlatformIncome);

        List<Map<String, Object>> incomesData;

        try {
            incomesData = instructorIncomes.getContent().stream().map(income -> {
                Map<String, Object> incomeData = new HashMap<>();

                OrderDto getInstructorProfileByUserId = orderService.findById(income.getOrderId(), correlationId);

                incomeData.put("income", InstructorIncomeMapper.mapToDto(income));
                incomeData.put("order", getInstructorProfileByUserId);

                return incomeData;
            }).toList();
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

        Map<String, Object> pagination = new HashMap<>();
        pagination.put("total_page", instructorIncomes.getTotalPages());
        pagination.put("per_page", instructorIncomes.getSize());
        pagination.put("current_page", instructorIncomes.getNumber() + 1);
        pagination.put("total_items", instructorIncomes.getTotalElements());

        Map<String, Object> incomes = new HashMap<>();
        incomes.put("data", incomesData);
        incomes.put("pagination", pagination);

        response.put("overview", overview);
        response.put("incomes", incomes);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data pendapatan kursus", response, null));
    }

}
