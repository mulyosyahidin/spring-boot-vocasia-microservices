package com.vocasia.order.controller;

import com.vocasia.order.config.FinanceConfigProperties;
import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.finance.InstructorBalanceDto;
import com.vocasia.order.dto.client.finance.InstructorIncomeDto;
import com.vocasia.order.dto.client.finance.PlatformBalanceDto;
import com.vocasia.order.dto.client.finance.PlatformIncomeDto;
import com.vocasia.order.dto.client.instructor.InstructorStudentCourseDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import com.vocasia.order.request.client.enrollment.EnrollNewCourseRequest;
import com.vocasia.order.request.client.finance.NewInstructorBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewInstructorIncomeRequest;
import com.vocasia.order.request.client.finance.NewPlatformBalanceHistoryRequest;
import com.vocasia.order.request.client.finance.NewPlatformIncomeRequest;
import com.vocasia.order.request.client.instructor.AssignCourseToStudentInstructorRequest;
import com.vocasia.order.service.*;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api")
@Validated
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final FinanceConfigProperties financeConfigProperties;

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IEnrollmentService enrollmentService;
    private final IFinanceService financeService;
    private final IInstructorService instructorService;

    public OrderController(FinanceConfigProperties financeConfigProperties, IOrderService iOrderService,
                           IOrderItemService iOrderItemService, IEnrollmentService iEnrollmentService,
                           IFinanceService iFinanceService, IInstructorService iInstructorService) {
        this.financeConfigProperties = financeConfigProperties;
        this.orderService = iOrderService;
        this.orderItemService = iOrderItemService;
        this.enrollmentService = iEnrollmentService;
        this.financeService = iFinanceService;
        this.instructorService = iInstructorService;
    }

    @GetMapping("/get-item-data/{orderId}/{courseId}")
    public ResponseEntity<ResponseDto> getOrderItemData(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                        @PathVariable("orderId") Long orderId, @PathVariable("courseId") Long courseId) {
        logger.info("OrderController.getOrderItemData called");

        OrderItem orderItem = orderItemService.findByOrderIdAndCourseId(orderId, courseId);

        Map<String, Object> response = new HashMap<>();
        response.put("item", OrderItemMapper.mapToDto(orderItem));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data item order", response, null));
    }

    @PutMapping("/update-payment-status/{orderId}")
    public ResponseEntity<ResponseDto> updateOrderPaymentStatus(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                @PathVariable Long orderId,
                                                                @RequestBody UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        logger.info("OrderController.updateOrderPaymentStatus called");

        Order order = orderService.findById(orderId);

        Map<String, Object> response = new HashMap<>();
        BigDecimal percentPlatformFee = financeConfigProperties.getPercentPlatformFee();

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

                    AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest = getAssignCourseToStudentInstructorRequest(orderItem, updatedOrder);
                    InstructorStudentCourseDto instructorStudentCourseDto = instructorService.assignCourse(assignCourseToStudentInstructorRequest, correlationId);

                    NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest = getNewInstructorBalanceHistoryRequest(percentPlatformFee, newInstructorIncomeRequest, order, orderItem);
                    InstructorBalanceDto instructorBalanceDto = financeService.saveInstructorBalance(newInstructorBalanceHistoryRequest, correlationId);

                    NewPlatformBalanceHistoryRequest newPlatformBalanceHistoryRequest = getNewPlatformBalanceHistoryRequest(percentPlatformFee, newInstructorIncomeRequest, order);
                    PlatformBalanceDto platformBalanceDto = financeService.savePlatformBalance(newPlatformBalanceHistoryRequest, correlationId);
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

    private static AssignCourseToStudentInstructorRequest getAssignCourseToStudentInstructorRequest(OrderItem orderItem, Order updatedOrder) {
        AssignCourseToStudentInstructorRequest assignCourseToStudentInstructorRequest = new AssignCourseToStudentInstructorRequest();

        assignCourseToStudentInstructorRequest.setInstructorId(orderItem.getCourseInstructorId());
        assignCourseToStudentInstructorRequest.setUserId(updatedOrder.getUserId());
        assignCourseToStudentInstructorRequest.setCourseId(orderItem.getCourseId());
        assignCourseToStudentInstructorRequest.setOrderId(updatedOrder.getId());

        return assignCourseToStudentInstructorRequest;
    }

    private static NewInstructorBalanceHistoryRequest getNewInstructorBalanceHistoryRequest(BigDecimal percentPlatformFee, NewInstructorIncomeRequest newInstructorIncomeRequest, Order order, OrderItem orderItem) {
        NewInstructorBalanceHistoryRequest newInstructorBalanceHistoryRequest = new NewInstructorBalanceHistoryRequest();

        Double platformFee = newInstructorIncomeRequest.getTotalUserPayment() * (percentPlatformFee.doubleValue() / 100);
        Double amount = newInstructorIncomeRequest.getTotalUserPayment() - (newInstructorIncomeRequest.getTotalUserPayment() * (percentPlatformFee.doubleValue() / 100));

        newInstructorBalanceHistoryRequest.setInstructorId(orderItem.getCourseInstructorId());
        newInstructorBalanceHistoryRequest.setType("income");
        newInstructorBalanceHistoryRequest.setAmount(amount);
        newInstructorBalanceHistoryRequest.setPlatformFee(platformFee);
        newInstructorBalanceHistoryRequest.setReferenceId(order.getId());
        newInstructorBalanceHistoryRequest.setReferenceType("order");
        newInstructorBalanceHistoryRequest.setTransactionStatus("completed");
        newInstructorBalanceHistoryRequest.setRemarks("New income from order #" + order.getOrderNumber());

        return newInstructorBalanceHistoryRequest;
    }

    private static NewPlatformBalanceHistoryRequest getNewPlatformBalanceHistoryRequest(BigDecimal percentPlatformFee, NewInstructorIncomeRequest newInstructorIncomeRequest, Order order) {
        NewPlatformBalanceHistoryRequest newPlatformBalanceHistoryRequest = new NewPlatformBalanceHistoryRequest();

        Double platformFee = newInstructorIncomeRequest.getTotalUserPayment() * (percentPlatformFee.doubleValue() / 100);

        newPlatformBalanceHistoryRequest.setType("fee");
        newPlatformBalanceHistoryRequest.setAmount(platformFee);
        newPlatformBalanceHistoryRequest.setReferenceId(order.getId());
        newPlatformBalanceHistoryRequest.setReferenceType("order");
        newPlatformBalanceHistoryRequest.setTransactionStatus("completed");
        newPlatformBalanceHistoryRequest.setRemarks("New fee income from order #" + order.getOrderNumber());
        newPlatformBalanceHistoryRequest.setRemarks("New fee income from order #" + order.getOrderNumber());

        return newPlatformBalanceHistoryRequest;
    }


}
