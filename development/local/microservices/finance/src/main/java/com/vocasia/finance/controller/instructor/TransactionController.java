package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.authentication.UserDto;
import com.vocasia.finance.dto.client.course.CourseDto;
import com.vocasia.finance.dto.client.order.OrderDto;
import com.vocasia.finance.dto.client.order.OrderItemDto;
import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.exception.CustomFeignException;
import com.vocasia.finance.mapper.InstructorIncomeMapper;
import com.vocasia.finance.service.IAuthenticationService;
import com.vocasia.finance.service.ICourseService;
import com.vocasia.finance.service.IInstructorIncomeService;
import com.vocasia.finance.service.IOrderService;
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
@RequestMapping("/api/instructor")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    private final IInstructorIncomeService instructorIncomeService;
    private final IOrderService orderService;
    private final ICourseService courseService;
    private final IAuthenticationService authenticationService;

    public TransactionController(IInstructorIncomeService iInstructorIncomeService, IOrderService iOrderService, ICourseService iCourseService, IAuthenticationService iAuthenticationService) {
        this.instructorIncomeService = iInstructorIncomeService;
        this.orderService = iOrderService;
        this.courseService = iCourseService;
        this.authenticationService = iAuthenticationService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<ResponseDto> getAllTransactions(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestHeader("X-INSTRUCTOR-ID") Long instructorId,
                                                          @RequestParam(defaultValue = "1") int page) {
        logger.info("TransactionController.getAllTransactions called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<InstructorIncome> instructorIncomes = instructorIncomeService.findAllByInstructorId(instructorId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> instructorIncomesData;

        try {
            instructorIncomesData = instructorIncomes.getContent().stream().map(instructorIncome -> {
                Map<String, Object> instructorIncomeData = new HashMap<>();

                CourseDto courseDto = courseService.findById(instructorIncome.getCourseId(), correlationId);
                OrderDto orderDto = orderService.findById(instructorIncome.getOrderId(), correlationId);
                OrderItemDto orderItemDto = orderService.findOrderItemById(instructorIncome.getOrderId(), instructorIncome.getCourseId(), correlationId);
                UserDto userDto = authenticationService.findUserById(orderDto.getUserId(), correlationId);

                instructorIncomeData.put("income", InstructorIncomeMapper.mapToDto(instructorIncome));
                instructorIncomeData.put("course", courseDto);
                instructorIncomeData.put("order", orderDto);
                instructorIncomeData.put("order_item", orderItemDto);
                instructorIncomeData.put("user", userDto);

                return instructorIncomeData;
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

        pagination.put("total_page", instructorIncomes.getTotalPages());
        pagination.put("per_page", instructorIncomes.getSize());
        pagination.put("current_page", instructorIncomes.getNumber() + 1);
        pagination.put("total_items", instructorIncomes.getTotalElements());

        response.put("data", instructorIncomesData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data transaksi", response, null));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<ResponseDto> getTransactionById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @PathVariable("id") Long id) {
        logger.info("TransactionController.getInstructorSaleById called");

        InstructorIncome instructorIncome = instructorIncomeService.findById(id);

        Map<String, Object> incomeData = new HashMap<>();
        incomeData.put("income", InstructorIncomeMapper.mapToDto(instructorIncome));

        try {
            CourseDto courseDto = courseService.findById(instructorIncome.getCourseId(), correlationId);
            OrderDto orderDto = orderService.findById(instructorIncome.getOrderId(), correlationId);
            OrderItemDto orderItemDto = orderService.findOrderItemById(instructorIncome.getOrderId(), instructorIncome.getCourseId(), correlationId);
            UserDto userDto = authenticationService.findUserById(orderDto.getUserId(), correlationId);

            incomeData.put("course", courseDto);
            incomeData.put("order", orderDto);
            incomeData.put("order_item", orderItemDto);
            incomeData.put("user", userDto);
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
                .body(new ResponseDto(true, "Berhasil mendapatkan detail pendapatan instruktur", incomeData, null));
    }

}
