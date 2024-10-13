package com.vocasia.order.controller.user;

import com.vocasia.order.dto.ResponseDto;
import com.vocasia.order.dto.client.authentication.UserDto;
import com.vocasia.order.dto.client.payment.PaymentDto;
import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.mapper.OrderItemMapper;
import com.vocasia.order.mapper.OrderMapper;
import com.vocasia.order.request.client.payment.CreateOrderPaymentRequest;
import com.vocasia.order.service.IAuthenticationService;
import com.vocasia.order.service.IOrderItemService;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.service.IPaymentService;
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
public class UserOrderController {

    private final Logger logger = LoggerFactory.getLogger(UserOrderController.class);

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final IPaymentService paymentService;
    private final IAuthenticationService authenticationService;

    public UserOrderController(IOrderService iOrderService, IOrderItemService iOrderItemService, IPaymentService iPaymentService, IAuthenticationService iAuthenticationService) {
        this.orderService = iOrderService;
        this.orderItemService = iOrderItemService;
        this.paymentService = iPaymentService;
        this.authenticationService = iAuthenticationService;
    }

    @GetMapping("/my-orders")
    public ResponseEntity<ResponseDto> getMyOrders(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                   @RequestHeader("X-USER-ID") Long userId) {
        logger.info("OrderController.getMyOrders called");

        List<Order> orders = orderService.findAllByUserId(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("orders", orders.stream().map(OrderMapper::mapToDto));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }

    @GetMapping("/my-orders/{orderId}")
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
                    .status(org.apache.hc.core5.http.HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data order", response, null));
    }


    private static List<CreateOrderPaymentRequest.Item> getItems(Order order) {
        List<CreateOrderPaymentRequest.Item> orderItems = new ArrayList<>(List.of());

        for (OrderItem orderItem : order.getOrderItems()) {
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
