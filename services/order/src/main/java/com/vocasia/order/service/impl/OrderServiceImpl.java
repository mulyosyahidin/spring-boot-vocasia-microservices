package com.vocasia.order.service.impl;

import com.vocasia.order.entity.Order;
import com.vocasia.order.entity.OrderItem;
import com.vocasia.order.exception.ResourceNotFoundException;
import com.vocasia.order.repository.OrderItemRepository;
import com.vocasia.order.repository.OrderRepository;
import com.vocasia.order.request.PlaceNewOrderRequest;
import com.vocasia.order.request.UpdatePaymentStatusRequest;
import com.vocasia.order.service.IOrderService;
import com.vocasia.order.types.PaymentStatus;
import com.vocasia.order.util.OrderNumberGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order placeNewOrder(PlaceNewOrderRequest placeNewOrderRequest) {
        Order order = new Order();

        List<PlaceNewOrderRequest.OrderItem> items = placeNewOrderRequest.getItems();
        double totalPrice = 0.0;
        double totalDiscount = 0.0;
        int totalItems = items.size();

        for (PlaceNewOrderRequest.OrderItem item : items) {
            double itemPrice = item.getCoursePrice();
            double discount = item.getCourseIsDiscount() ? item.getCourseDiscount() : 0.0;

            totalPrice += (itemPrice - discount);
            totalDiscount += discount;

            OrderItem orderItem = new OrderItem();

            orderItem.setCourseId(item.getCourseId());
            orderItem.setCourseTitle(item.getCourseTitle());
            orderItem.setCourseSlug(item.getCourseSlug());
            orderItem.setCourseFeaturedPictureUrl(item.getCourseFeaturedPictureUrl());
            orderItem.setCoursePrice(itemPrice);
            orderItem.setCourseIsFree(item.getCourseIsFree());
            orderItem.setCourseIsDiscount(item.getCourseIsDiscount());
            orderItem.setCourseDiscount(item.getCourseDiscount());
            orderItem.setCourseSubtotal(item.getCourseIsDiscount() ? (itemPrice - discount) : itemPrice);
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        }

        order.setUserId(placeNewOrderRequest.getUserId());
        order.setPaymentStatus(PaymentStatus.PENDING.name());
        order.setOrderNumber(OrderNumberGenerator.generate(placeNewOrderRequest.getUserId()));
        order.setTotalItems(totalItems);
        order.setTotalPrice(totalPrice);
        order.setTotalDiscount(totalDiscount);

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Data tidak ditemukan")
        );
    }

    @Override
    public Order updatePaymentStatus(Long orderId, UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setPaymentStatus(updatePaymentStatusRequest.getStatus());

            return orderRepository.save(order);
        }

        return null;
    }


}
