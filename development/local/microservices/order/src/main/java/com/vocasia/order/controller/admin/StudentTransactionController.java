package com.vocasia.order.controller.admin;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.authentication.UserDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.service.IOrderService;
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
@RequestMapping("/api/admin/student")
public class StudentTransactionController {

    private final Logger logger = LoggerFactory.getLogger(StudentTransactionController.class);

    private final IOrderService orderService;

    public StudentTransactionController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/transactions")
    public ResponseEntity<ResponseDto> getAllStudentTransactions(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                 @RequestHeader("X-USER-ID") Long userId,
                                                                 @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentTransactionController.getAllStudentTransactions called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Order> orders = orderService.findAllByUserId(userId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", orders.getTotalPages());
        pagination.put("per_page", orders.getSize());
        pagination.put("current_page", orders.getNumber() + 1);
        pagination.put("total_items", orders.getTotalElements());

        response.put("data", orders.getContent().stream().map(OrderMapper::mapToDto));
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data transaksi siswa", response, null));
    }

}
