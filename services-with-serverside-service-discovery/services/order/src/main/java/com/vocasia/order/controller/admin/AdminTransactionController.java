package com.vocasia.order.controller.admin;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.authentication.UserDto;
import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.service.*;
import org.apache.hc.core5.http.HttpStatus;
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
@RequestMapping("/api/admin")
public class AdminTransactionController {

    private final Logger logger = LoggerFactory.getLogger(AdminTransactionController.class);

    private final IAuthenticationService authenticationService;
    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IPaymentService paymentService;
    private final IFinanceService financeService;

    public AdminTransactionController(IAuthenticationService iAuthenticationService, IOrderService iOrderService,
                                      IOrderItemService iOrderItemService, IPaymentService iPaymentService,
                                      IFinanceService iFinanceService) {
        this.authenticationService = iAuthenticationService;
        this.orderService = iOrderService;
        this.orderItemService = iOrderItemService;
        this.paymentService = iPaymentService;
        this.financeService = iFinanceService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<ResponseDto> getAllTransactions(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @RequestParam(defaultValue = "1") int page) {
        logger.info("AdminTransactionController.getAllTransactions called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Order> orders = orderService.findAll(paging);

        List<Map<String, Object>> ordersData;

        try {
            ordersData = orders.getContent().stream().map(order -> {
                Map<String, Object> orderData = new HashMap<>();

                UserDto userDto = authenticationService.findUserById(order.getUserId(), correlationId);

                orderData.put("order", OrderMapper.mapToDto(order));
                orderData.put("user", userDto);

                return orderData;
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

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", orders.getTotalPages());
        pagination.put("per_page", orders.getSize());
        pagination.put("current_page", orders.getNumber() + 1);
        pagination.put("total_items", orders.getTotalElements());

        response.put("data", ordersData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data transaksi", response, null));
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<ResponseDto> getTransactionById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                          @PathVariable("id") Long id) {
        logger.info("AdminTransactionController.getTransactionById called");

        Order order = orderService.findById(id);
        List<OrderItem> orderItems = orderItemService.findAllByOrderId(id);

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(order));
        response.put("items", orderItems.stream().map(OrderItemMapper::mapToDto));

        try {
            UserDto userDto = authenticationService.findUserById(order.getUserId(), correlationId);
            PaymentDto paymentDto = paymentService.findByOrderId(order.getId(), correlationId);

            response.put("user", userDto);
            response.put("payment", paymentDto);

            List<Map<String, Object>> instructorIncomesDto = financeService.findInstructorIncomesByOrderId(order.getId(), correlationId);
            List<Map<String, Object>> platformIncomesDto = financeService.findPlatformIncomesByOrderId(order.getId(), correlationId);

            response.put("instructor_incomes", instructorIncomesDto);
            response.put("platform_incomes", platformIncomesDto);
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
                .body(new ResponseDto(true, "Berhasil mendapatkan data transaksi", response, null));
    }

}
