package com.vocasia.order.controller.student;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.authentication.UserDto;
import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.client.payment.CreateOrderPaymentRequest;
import com.vocasia.order.service.IAuthenticationService;
import com.vocasia.order.service.IOrderItemService;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.service.IPaymentService;
import jakarta.validation.Valid;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/student")
public class StudentOrderController {

    private final Logger logger = LoggerFactory.getLogger(StudentOrderController.class);

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IPaymentService paymentService;
    private final IAuthenticationService authenticationService;

    public StudentOrderController(IOrderService iOrderService, IOrderItemService iOrderItemService, IPaymentService iPaymentService, IAuthenticationService iAuthenticationService) {
        this.orderService = iOrderService;
        this.orderItemService = iOrderItemService;
        this.paymentService = iPaymentService;
        this.authenticationService = iAuthenticationService;
    }

    @PostMapping("/place-new-order")
    public ResponseEntity<ResponseDto> placeNewOrder(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                     @RequestHeader("X-USER-ID") Long userId,
                                                     @Valid @RequestBody PlaceNewOrderRequest placeNewOrderRequest) {
        logger.info("StudentOrderController.placeNewOrder called");

        Order createdOrder = orderService.placeNewOrder(userId, placeNewOrderRequest);
        CreateOrderPaymentRequest createOrderPaymentRequest = getCreateOrderPaymentRequest(placeNewOrderRequest, createdOrder);

        Map<String, Object> response = new HashMap<>();
        response.put("order", OrderMapper.mapToDto(createdOrder));

        try {
            PaymentDto paymentDto = paymentService.saveOrderPayment(createOrderPaymentRequest, correlationId);

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
                .body(new ResponseDto(true, "Berhasil melakukan order. Silahkan lakukan pembayaran dalam 24 jam", response, null));
    }

    @GetMapping("/orders")
    public ResponseEntity<ResponseDto> getAllOrders(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @RequestHeader("X-USER-ID") Long userId,
                                                    @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentOrderController.getAllOrders called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<Order> orders = orderService.findAllByUserId(userId, paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> studentOrdersData = orders.getContent().stream().map(order -> {
            Map<String, Object> studentOrderData = new HashMap<>();

            List<OrderItem> orderItems = orderItemService.findAllByOrderId(order.getId());

            studentOrderData.put("order", OrderMapper.mapToDto(order));
            studentOrderData.put("items", orderItems.stream().map(OrderItemMapper::mapToDto));

            return studentOrderData;
        }).toList();

        pagination.put("total_page", orders.getTotalPages());
        pagination.put("per_page", orders.getSize());
        pagination.put("current_page", orders.getNumber() + 1);
        pagination.put("total_items", orders.getTotalElements());

        response.put("data", studentOrdersData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }

    @GetMapping("/orders/{orderId}")
    public ResponseEntity<ResponseDto> findMyOrderById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                       @PathVariable("orderId") Long orderId) {
        logger.info("OrderController.findMyOrderById called");

        Order order = orderService.findById(orderId);
        List<OrderItem> orderItems = orderItemService.findAllByOrderId(orderId);

        Map<String, Object> response = new HashMap<>();

        response.put("order", OrderMapper.mapToDto(order));
        response.put("items", orderItems.stream().map(OrderItemMapper::mapToDto));

        try {
            PaymentDto paymentDto = paymentService.findByOrderId(orderId, correlationId);

            response.put("payment", paymentDto);
        } catch (CustomFeignException e) {
            if (e.getHttpStatus() == HttpStatus.SC_NOT_FOUND) {
                logger.debug("Payment not found for order with ID: {}", orderId);
                logger.debug("Requesting new payment...");

                UserDto userDto = authenticationService.findUserById(order.getUserId(), correlationId);

                CreateOrderPaymentRequest createOrderPaymentRequest = new CreateOrderPaymentRequest();
                createOrderPaymentRequest.setOrderId(orderId);
                createOrderPaymentRequest.setOrderNumber(order.getOrderNumber());
                createOrderPaymentRequest.setTotalPrice(order.getTotalPrice());

                List<CreateOrderPaymentRequest.Item> newOrderItems = getItems(order);
                createOrderPaymentRequest.setItems(newOrderItems);

                CreateOrderPaymentRequest.Customer customer = new CreateOrderPaymentRequest.Customer();
                customer.setId(userDto.getId());
                customer.setName(userDto.getName());
                customer.setEmail(userDto.getEmail());
                createOrderPaymentRequest.setCustomer(customer);

                PaymentDto paymentDto = paymentService.saveOrderPayment(createOrderPaymentRequest, correlationId);

                logger.info("Payment created for order with ID: {}", orderId);

                response.put("payment", paymentDto);
            } else {
                logger.error(e.getMessage(), e);

                return ResponseEntity
                        .status(e.getHttpStatus())
                        .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
            }
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

    private static CreateOrderPaymentRequest getCreateOrderPaymentRequest(PlaceNewOrderRequest placeNewOrderRequest, Order createdOrder) {
        List<CreateOrderPaymentRequest.Item> orderItems = getItems(createdOrder);

        CreateOrderPaymentRequest createOrderPaymentRequest = new CreateOrderPaymentRequest();

        CreateOrderPaymentRequest.Customer customer = new CreateOrderPaymentRequest.Customer();
        customer.setId(placeNewOrderRequest.getCustomer().getId());
        customer.setName(placeNewOrderRequest.getCustomer().getName());
        customer.setEmail(placeNewOrderRequest.getCustomer().getEmail());

        createOrderPaymentRequest.setOrderId(createdOrder.getId());
        createOrderPaymentRequest.setTotalPrice(createdOrder.getTotalPrice());
        createOrderPaymentRequest.setOrderNumber(createdOrder.getOrderNumber());
        createOrderPaymentRequest.setItems(orderItems);
        createOrderPaymentRequest.setCustomer(customer);

        return createOrderPaymentRequest;
    }

    private static List<CreateOrderPaymentRequest.Item> getItems(Order createdOrder) {
        List<CreateOrderPaymentRequest.Item> orderItems = new ArrayList<>(List.of());

        for (OrderItem orderItem : createdOrder.getOrderItems()) {
            CreateOrderPaymentRequest.Item item = new CreateOrderPaymentRequest.Item();

            item.setCourseId(orderItem.getCourseId());
            item.setCourseTitle(orderItem.getCourseTitle());
            item.setCoursePrice(orderItem.getCoursePrice());
            item.setCourseDiscount(orderItem.getCourseDiscount());

            orderItems.add(item);
        }

        return orderItems;
    }

}
