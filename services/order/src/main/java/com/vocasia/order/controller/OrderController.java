package com.vocasia.order.controller;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.EnrollmentDto;
import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.mapper.client.EnrollmentMapper;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import com.vocasia.order.request.client.EnrollNewCourseRequest;
import com.vocasia.order.service.IEnrollmentService;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Order Controller", description = "Controller untuk melakukan order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final IOrderService orderService;
    private final IPaymentService paymentService;
    private final IEnrollmentService enrollmentService;

    public OrderController(IOrderService orderService, IPaymentService paymentService, IEnrollmentService enrollmentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/place-new-order")
    public ResponseEntity<ResponseDto> placeNewOrder(@RequestHeader("vocasia-correlation-id")
                                                     String correlationId, @Valid @RequestBody PlaceNewOrderRequest placeNewOrderRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            Order createdOrder = orderService.placeNewOrder(placeNewOrderRequest);

            CreateOrderPaymentRequest createOrderPaymentRequest = new CreateOrderPaymentRequest();

            createOrderPaymentRequest.setOrderId(createdOrder.getId());
            createOrderPaymentRequest.setTotalPrice(createdOrder.getTotalPrice());
            createOrderPaymentRequest.setOrderNumber(createdOrder.getOrderNumber());

            PaymentDto paymentDto = paymentService.createOrderPayment(createOrderPaymentRequest);

            response.put("order", OrderMapper.mapToDto(createdOrder));
            response.put("payment", paymentDto);
        } catch (CustomFeignException ex) {
            logger.error("Validation error: {}", ex.getErrors());

            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(false, "Validation error", null, ex.getErrors()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal menambah data order baru", null, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(true, "Berhasil menambah data order baru", response, null));
    }

    @GetMapping("/get-data/{orderId}")
    public ResponseEntity<ResponseDto> getData(@RequestHeader("vocasia-correlation-id")
                                               String correlationId, @PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Order order = orderService.getOrderById(orderId);

            PaymentDto paymentDto = paymentService.getPaymentDataByOrderId(orderId);

            response.put("order", OrderMapper.mapToDto(order));
            response.put("payment", paymentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal mendapatkan data order", null, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }

    @PutMapping("/update-payment-status/{orderId}")
    public ResponseEntity<ResponseDto> updatePaymentStatus(@PathVariable Long orderId, @RequestBody UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        Map<String, Object> response = new HashMap<>();

        try {
            Order updatedOrder = orderService.updatePaymentStatus(orderId, updatePaymentStatusRequest);

            response.put("order", OrderMapper.mapToDto(updatedOrder));

            if (Objects.equals(updatePaymentStatusRequest.getTransactionStatus(), "settlement")) {
                EnrollNewCourseRequest enrollNewCourseRequest = getEnrollNewCourseRequest(updatedOrder);
                List<EnrollmentDto> enrollmentDtos = enrollmentService.enrollNewCourse(enrollNewCourseRequest);

                response.put("enrollments", enrollmentDtos);
            }
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal memperbarui status pembayaran", null, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(true, "Berhasil memperbarui status pembayaran", response, null));
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
