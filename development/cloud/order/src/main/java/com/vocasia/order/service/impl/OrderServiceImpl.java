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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Order placeNewOrder(Long userId, PlaceNewOrderRequest placeNewOrderRequest) {
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
            orderItem.setCourseInstructorId(item.getCourseInstructorId());
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

        order.setUserId(userId);
        order.setPaymentStatus(PaymentStatus.PENDING.name());
        order.setOrderNumber(OrderNumberGenerator.generate(userId));
        order.setTotalItems(totalItems);
        order.setTotalPrice(totalPrice);
        order.setTotalDiscount(totalDiscount);

        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Data tidak ditemukan")
        );
    }

    @Override
    public Order updateOrderPaymentStatus(Order order, UpdatePaymentStatusRequest updatePaymentStatusRequest) {
        order.setPaymentStatus(updatePaymentStatusRequest.getStatus());

        return orderRepository.save(order);
    }

    @Override
    public Page<Order> findAllByUserId(Long userId, Pageable paging) {
        return orderRepository.findAllByUserIdOrderByCreatedAtDesc(userId, paging);
    }

    @Override
    public Page<Order> findAll(Pageable paging) {
        return orderRepository.findAll(paging);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public long countByPaymentStatus(String status) {
        return orderRepository.countByPaymentStatus(status);
    }

    @Override
    public double sumTotalPrice() {
        return orderRepository.sumTotalPrice();
    }

    @Override
    public double sumTotalPriceByStatus(String status) {
        return orderRepository.sumTotalPriceByStatus(status);
    }

}
