package com.vocasia.order.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.order.dto.client.PaymentDto;
import com.vocasia.order.exception.CustomFeignException;
import com.vocasia.order.request.client.CreateOrderPaymentRequest;
import com.vocasia.order.service.IPaymentService;
import com.vocasia.order.service.client.PaymentFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentFeignClient paymentFeignClient;
    private final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Override
    public PaymentDto createOrderPayment(CreateOrderPaymentRequest createOrderPaymentRequest) {
        try {
            ResponseEntity<Map<String, Object>> createOrderPaymentResponseEntity = paymentFeignClient.createOrderPayment(createOrderPaymentRequest);
            logger.info("createOrderPaymentResponseEntity: {}", createOrderPaymentResponseEntity);

            Map<String, Object> responseBody = createOrderPaymentResponseEntity.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> payment = (Map<String, Object>) data.get("payment");

                    PaymentDto paymentDto = new PaymentDto();

                    paymentDto.setId(Long.valueOf(payment.get("id").toString()));
                    paymentDto.setOrderId(Long.valueOf(payment.get("order_id").toString()));
                    paymentDto.setOrderNumber((String) payment.get("order_number"));
                    paymentDto.setTotalPrice((Double) payment.get("total_price"));
                    paymentDto.setAdditionalFee((Double) payment.get("additional_fee"));
                    paymentDto.setTotalPayment((Double) payment.get("total_payment"));
                    paymentDto.setSnapToken((String) payment.get("snap_token"));
                    paymentDto.setPaymentStatus((String) payment.get("payment_status"));
                    paymentDto.setPaymentExpireAt(LocalDateTime.parse((String) payment.get("payment_expire_at")));
                    paymentDto.setIsExpired((Boolean) payment.get("is_expired"));
                    paymentDto.setCreatedAt(LocalDateTime.parse((String) payment.get("created_at")));
                    paymentDto.setUpdatedAt(LocalDateTime.parse((String) payment.get("updated_at")));

                    return paymentDto;
                }
            } else {
                logger.warn("createOrderPaymentResponseEntity: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            try {
                Map<String, Object> responseBody = new ObjectMapper().readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
                Object errors = responseBody.get("errors");

                throw new CustomFeignException("Validation error", errors);
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to parse error response", ioException);
            }
        }
    }

    @Override
    public PaymentDto getPaymentDataByOrderId(Long orderId) {
        try {
            ResponseEntity<Map<String, Object>> getPaymentDataResponseEntity = paymentFeignClient.getPaymentDataByOrderId(orderId);
            logger.info("getPaymentDataResponseEntity: {}", getPaymentDataResponseEntity);

            Map<String, Object> responseBody = getPaymentDataResponseEntity.getBody();

            if (responseBody != null && responseBody.get("success") != null && (Boolean) responseBody.get("success")) {
                Map<String, Object> data = (Map<String, Object>) responseBody.get("data");

                if (data != null) {
                    Map<String, Object> payment = (Map<String, Object>) data.get("payment");

                    PaymentDto paymentDto = new PaymentDto();

                    paymentDto.setId(Long.valueOf(payment.get("id").toString()));
                    paymentDto.setOrderId(Long.valueOf(payment.get("order_id").toString()));
                    paymentDto.setOrderNumber((String) payment.get("order_number"));
                    paymentDto.setTotalPrice((Double) payment.get("total_price"));
                    paymentDto.setAdditionalFee((Double) payment.get("additional_fee"));
                    paymentDto.setTotalPayment((Double) payment.get("total_payment"));
                    paymentDto.setSnapToken((String) payment.get("snap_token"));
                    paymentDto.setPaymentStatus((String) payment.get("payment_status"));
                    paymentDto.setPaymentExpireAt(LocalDateTime.parse((String) payment.get("payment_expire_at")));
                    paymentDto.setIsExpired((Boolean) payment.get("is_expired"));
                    paymentDto.setCreatedAt(LocalDateTime.parse((String) payment.get("created_at")));
                    paymentDto.setUpdatedAt(LocalDateTime.parse((String) payment.get("updated_at")));

                    return paymentDto;
                }
            } else {
                logger.warn("getPaymentDataResponseEntity: {}", responseBody != null ? responseBody.get("message") : "No response body");
            }

            return null;
        } catch (FeignException e) {
            try {
                Map<String, Object> responseBody = new ObjectMapper().readValue(e.contentUTF8(), new TypeReference<Map<String, Object>>() {});
                Object errors = responseBody.get("errors");

                throw new CustomFeignException("Validation error", errors);
            } catch (IOException ioException) {
                throw new RuntimeException("Failed to parse error response", ioException);
            }
        }
    }

}
