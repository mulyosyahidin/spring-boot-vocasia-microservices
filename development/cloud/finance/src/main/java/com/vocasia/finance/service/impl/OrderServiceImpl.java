package com.vocasia.finance.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.order.OrderDto;
import com.vocasia.finance.dto.client.order.OrderItemDto;
import com.vocasia.finance.exception.CustomFeignException;
import com.vocasia.finance.service.IOrderService;
import com.vocasia.finance.service.client.OrderFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderFeignClient orderFeignClient;

    @Cacheable(value = "orders", key = "#orderId")
    @Override
    public OrderDto findById(Long orderId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> orderFeignClientFindByIdResponseEntity = orderFeignClient.findById(correlationId, orderId);
            ResponseDto responseBody = orderFeignClientFindByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> order = data != null ? (Map<String, Object>) data.get("order") : null;

            logger.debug("OrderServiceImpl.findById() $order:: {}", order);

            OrderDto orderDto = new OrderDto();

            if (order != null) {
                orderDto.setId(Long.valueOf(order.get("id").toString()));
                orderDto.setUserId(Long.valueOf(order.get("user_id").toString()));
                orderDto.setOrderNumber(order.get("order_number").toString());
                orderDto.setTotalItems(Integer.parseInt(order.get("total_items").toString()));
                orderDto.setTotalPrice(Double.parseDouble(order.get("total_price").toString()));
                orderDto.setTotalDiscount(Double.parseDouble(order.get("total_discount").toString()));
                orderDto.setPaymentStatus(order.get("payment_status").toString());
                orderDto.setCreatedAt(order.get("created_at").toString());
                orderDto.setUpdatedAt(order.get("updated_at").toString());
            }

            return orderDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

    @Cacheable(value = "orderItems", key = "#orderId")
    @Override
    public OrderItemDto findOrderItemById(Long orderId, Long courseId, String correlationId) {
        try {
            ResponseEntity<ResponseDto> orderFeignClientFindByIdResponseEntity = orderFeignClient.findOrderItemById(correlationId, orderId, courseId);
            ResponseDto responseBody = orderFeignClientFindByIdResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();
            Map<String, Object> item = data != null ? (Map<String, Object>) data.get("item") : null;

            logger.debug("OrderServiceImpl.findOrderItemById() $item:: {}", item);

            OrderItemDto orderItemDto = new OrderItemDto();

            if (item != null) {
                orderItemDto.setId(Long.valueOf(item.get("id").toString()));
                orderItemDto.setOrderId(Long.valueOf(item.get("order_id").toString()));
                orderItemDto.setCourseId(Long.valueOf(item.get("course_id").toString()));
                orderItemDto.setCourseInstructorId(Long.valueOf(item.get("course_instructor_id").toString()));
                orderItemDto.setCourseTitle(item.get("course_title").toString());
                orderItemDto.setCourseSlug(item.get("course_slug").toString());
                orderItemDto.setCourseFeaturedPictureUrl(item.get("course_featured_picture_url").toString());
                orderItemDto.setCoursePrice(Double.parseDouble(item.get("course_price").toString()));
                orderItemDto.setCourseIsFree(Boolean.parseBoolean(item.get("course_is_free").toString()));
                orderItemDto.setCourseIsDiscount(Boolean.parseBoolean(item.get("course_is_discount").toString()));
                orderItemDto.setCourseDiscount(Double.parseDouble(item.get("course_discount").toString()));
                orderItemDto.setCourseSubtotal(Double.parseDouble(item.get("course_subtotal").toString()));
            }

            return orderItemDto;
        } catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
