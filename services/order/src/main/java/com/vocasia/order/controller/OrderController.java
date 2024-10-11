package com.vocasia.order.controller;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.finance.InstructorIncomeDto;
import com.vocasia.order.dto.client.finance.PlatformIncomeDto;
import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import com.vocasia.order.request.client.enrollment.EnrollNewCourseRequest;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;
import com.vocasia.order.request.client.order.CreateOrderPaymentRequest;
import com.vocasia.order.service.IEnrollmentService;
import com.vocasia.order.service.IFinanceService;
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
    private final IFinanceService financeService;

    public OrderController(IOrderService iOrderService, IPaymentService iPaymentService, IEnrollmentService iEnrollmentService, IFinanceService iFinanceService) {
        this.orderService = iOrderService;
        this.paymentService = iPaymentService;
        this.enrollmentService = iEnrollmentService;
        this.financeService = iFinanceService;
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

    @PutMapping("/update-payment-status/{orderId}")
    public ResponseEntity<ResponseDto> updateOrderPaymentStatus(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                @PathVariable Long orderId, @RequestBody UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        logger.debug("OrderController.updateOrderPaymentStatus called");

        Order order = orderService.findById(orderId);

        Map<String, Object> response = new HashMap<>();

        if (Objects.equals(updatePaymentStatusRequest.getTransactionStatus(), "settlement")) {
            try {
                Order updatedOrder = orderService.updateOrderPaymentStatus(order, updatePaymentStatusRequest);
                response.put("order", OrderMapper.mapToDto(updatedOrder));

                EnrollNewCourseRequest enrollNewCourseRequest = getEnrollNewCourseRequest(updatedOrder);
                enrollmentService.enrollNewCourse(enrollNewCourseRequest, correlationId);

                List<OrderItem> orderItems = updatedOrder.getOrderItems();

                for (OrderItem orderItem : orderItems) {
                    NewInstructorIncomeRequest newInstructorIncomeRequest = getNewInstructorIncomeRequest(orderItem, updatedOrder);
                    InstructorIncomeDto instructorIncomeDto = financeService.saveInstructorIncome(newInstructorIncomeRequest, correlationId);

                    NewPlatformIncomeRequest newPlatformIncomeRequest = getNewPlatformIncomeRequest(orderItem, updatedOrder);
                    PlatformIncomeDto platformIncomeDto = financeService.savePlatformIncome(newPlatformIncomeRequest, correlationId);
                }
            } catch (CustomFeignException e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            } catch (Exception e) {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                        .body(new ResponseDto(false, "Internal server error", null, null));
            }
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil memperbarui status pembayaran", response, null));
    }

    private static NewPlatformIncomeRequest getNewPlatformIncomeRequest(OrderItem orderItem, Order updatedOrder) {
        NewPlatformIncomeRequest newPlatformIncomeRequest = new NewPlatformIncomeRequest();

        newPlatformIncomeRequest.setInstructorId(orderItem.getCourseInstructorId());
        newPlatformIncomeRequest.setOrderId(updatedOrder.getId());
        newPlatformIncomeRequest.setCourseId(orderItem.getCourseId());
        newPlatformIncomeRequest.setTotalUserPayment(orderItem.getCourseSubtotal());
        newPlatformIncomeRequest.setRemarks("New income from order #" + updatedOrder.getOrderNumber());

        return newPlatformIncomeRequest;
    }

    private static NewInstructorIncomeRequest getNewInstructorIncomeRequest(OrderItem orderItem, Order updatedOrder) {
        NewInstructorIncomeRequest newInstructorIncomeRequest = new NewInstructorIncomeRequest();

        newInstructorIncomeRequest.setInstructorId(orderItem.getCourseInstructorId());
        newInstructorIncomeRequest.setOrderId(updatedOrder.getId());
        newInstructorIncomeRequest.setCourseId(orderItem.getCourseId());
        newInstructorIncomeRequest.setTotalUserPayment(orderItem.getCourseSubtotal());
        newInstructorIncomeRequest.setRemarks("New income from order #" + updatedOrder.getOrderNumber());
        
        return newInstructorIncomeRequest;
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
