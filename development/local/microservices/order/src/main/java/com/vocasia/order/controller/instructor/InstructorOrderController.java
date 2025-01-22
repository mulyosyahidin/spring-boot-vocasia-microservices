package com.vocasia.order.controller.instructor;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.service.IPaymentService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor")
public class InstructorOrderController {

    private final Logger logger = LoggerFactory.getLogger(InstructorOrderController.class);

    private final IOrderService orderService;
    private final IPaymentService paymentService;

    public InstructorOrderController(IOrderService iOrderService, IPaymentService iPaymentService) {
        this.orderService = iOrderService;
        this.paymentService = iPaymentService;
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseDto> getOrderById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @PathVariable Long orderId) {
        logger.info("OrderController.getOrderById called");

        Order order = orderService.findById(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(order));
        response.put("items", order.getOrderItems().stream().map(OrderItemMapper::mapToDto));

        try {
            PaymentDto paymentDto = paymentService.findByOrderId(orderId, correlationId);

            response.put("payment", paymentDto);
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
                .body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }

}
