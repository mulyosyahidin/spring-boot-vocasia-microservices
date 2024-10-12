package com.vocasia.finance.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class InstructorSalesController {

    private final Logger logger = LoggerFactory.getLogger(InstructorSalesController.class);

    private final IInstructorIncomeService instructorIncomeService;
    private final IOrderService orderService;
    private final ICourseService courseService;
    private final IAuthenticationService authenticationService;

    public InstructorSalesController(IInstructorIncomeService iInstructorIncomeService, IOrderService iOrderService, ICourseService iCourseService, IAuthenticationService iAuthenticationService) {
        this.instructorIncomeService = iInstructorIncomeService;
        this.orderService = iOrderService;
        this.courseService = iCourseService;
        this.authenticationService = iAuthenticationService;
    }

    @GetMapping("/instructor-sales")
    public ResponseEntity<ResponseDto> getAllInstructorSales(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("InstructorSalesController.getAllInstructorSales called");

        List<InstructorIncome> instructorIncomes = instructorIncomeService.getAllInstructorIncome(instructorId);

        Map<String, Object> response = new HashMap<>();
        List<Map<String, Object>> incomesData = new ArrayList<>();

        for (InstructorIncome instructorIncome : instructorIncomes) {
            Map<String, Object> incomeData = new HashMap<>();

            incomeData.put("income", InstructorIncomeMapper.mapToDto(instructorIncome));

            try {
                CourseDto courseDto = courseService.findById(instructorIncome.getCourseId(), correlationId);
                incomeData.put("course", courseDto);

                OrderDto orderDto = orderService.findById(instructorIncome.getOrderId(), correlationId);
                incomeData.put("order", orderDto);

                OrderItemDto orderItemDto = orderService.findOrderItemById(instructorIncome.getOrderId(), instructorIncome.getCourseId(), correlationId);
                incomeData.put("order_item", orderItemDto);

                UserDto userDto = authenticationService.findUserById(orderDto.getUserId(), correlationId);
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

            incomesData.add(incomeData);
        }

        response.put("incomes", incomesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data penjualan instruktur", response, null));
    }

    @GetMapping("/instructor-sales/{incomeId}")
    public ResponseEntity<ResponseDto> getInstructorSaleById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @PathVariable("incomeId") Long incomeId) {
        logger.info("InstructorSalesController.getInstructorSaleById called");

        InstructorIncome instructorIncome = instructorIncomeService.findById(incomeId);

        Map<String, Object> incomeData = new HashMap<>();
        incomeData.put("income", InstructorIncomeMapper.mapToDto(instructorIncome));

        try {
            CourseDto courseDto = courseService.findById(instructorIncome.getCourseId(), correlationId);
            incomeData.put("course", courseDto);

            OrderDto orderDto = orderService.findById(instructorIncome.getOrderId(), correlationId);
            incomeData.put("order", orderDto);

            OrderItemDto orderItemDto = orderService.findOrderItemById(instructorIncome.getOrderId(), instructorIncome.getCourseId(), correlationId);
            incomeData.put("order_item", orderItemDto);

            UserDto userDto = authenticationService.findUserById(orderDto.getUserId(), correlationId);
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
