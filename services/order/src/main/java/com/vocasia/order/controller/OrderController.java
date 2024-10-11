package com.vocasia.order.controller;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import com.vocasia.order.request.client.EnrollNewCourseRequest;
import com.vocasia.order.service.IEnrollmentService;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.service.IPaymentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@Validated
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final IOrderService orderService;
    private final IPaymentService paymentService;
    private final IEnrollmentService enrollmentService;

    public OrderController(IOrderService iOrderService, IPaymentService iPaymentService, IEnrollmentService iEnrollmentService) {
        this.orderService = iOrderService;
        this.paymentService = iPaymentService;
        this.enrollmentService = iEnrollmentService;
    }

    @PostMapping("/place-new-order")
    public ResponseEntity<ResponseDto> placeNewOrder(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @Valid @RequestBody PlaceNewOrderRequest placeNewOrderRequest) {
        logger.debug("OrderController.placeNewOrder called");

        Order createdOrder = orderService.placeNewOrder(placeNewOrderRequest);

        CreateOrderPaymentRequest createOrderPaymentRequest = new CreateOrderPaymentRequest();
        createOrderPaymentRequest.setOrderId(createdOrder.getId());
        createOrderPaymentRequest.setTotalPrice(createdOrder.getTotalPrice());
        createOrderPaymentRequest.setOrderNumber(createdOrder.getOrderNumber());

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(createdOrder));

        try {
            PaymentDto paymentDto = paymentService.createOrderPayment(createOrderPaymentRequest, correlationId);

            response.put("payment", paymentDto);
        } catch (CustomFeignException e) {
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menambah data order baru", response, null));
    }

    @GetMapping("/get-data/{orderId}")
    public ResponseEntity<ResponseDto> getOrderData(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                    @PathVariable Long orderId) {
        logger.debug("OrderController.getOrderData called");

        Order order = orderService.findById(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(order));
        response.put("items", order.getOrderItems().stream().map(OrderItemMapper::mapToDto));

        try {
            PaymentDto paymentDto = paymentService.getPaymentDataByOrderId(orderId, correlationId);

            response.put("payment", paymentDto);
        } catch (CustomFeignException e) {
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }

    @PutMapping("/update-payment-status/{orderId}")
    public ResponseEntity<ResponseDto> updateOrderPaymentStatus(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                @PathVariable Long orderId, @RequestBody UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        logger.debug("OrderController.updateOrderPaymentStatus called");

        Order order = orderService.findById(orderId);
        Order updatedOrder = orderService.updateOrderPaymentStatus(order, updatePaymentStatusRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(updatedOrder));

        if (Objects.equals(updatePaymentStatusRequest.getTransactionStatus(), "settlement")) {
            try {
                EnrollNewCourseRequest enrollNewCourseRequest = getEnrollNewCourseRequest(updatedOrder);
                enrollmentService.enrollNewCourse(enrollNewCourseRequest, correlationId);
            } catch (CustomFeignException e) {
                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, "Internal server error", null, null));
            }
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui status pembayaran", response, null));
    }

    private static EnrollNewCourseRequest getEnrollNewCourseRequest(Order updatedOrder) {
        EnrollNewCourseRequest enrollNewCourseRequest = new EnrollNewCourseRequest();
        List<EnrollNewCourseRequest.CourseRequest> courses = new ArrayList<>();

        for (OrderItem orderItem : updatedOrder.getOrderItems()) {
            EnrollNewCourseRequest.CourseRequest course = new EnrollNewCourseRequest.CourseRequest();

            course.setCourseId(orderItem.getCourseId());
            courses.add(course);
        }

        enrollNewCourseRequest.setUserId(updatedOrder.getUserId());
        enrollNewCourseRequest.setOrderId(updatedOrder.getId());
        enrollNewCourseRequest.setCourses(courses);

        return enrollNewCourseRequest;
    }

}
