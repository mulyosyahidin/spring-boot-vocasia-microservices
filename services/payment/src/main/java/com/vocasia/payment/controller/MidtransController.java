package com.vocasia.payment.controller;

import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.dto.feign.OrderDto;
import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.mapper.PaymentMapper;
import com.vocasia.payment.request.MidtransCallbackRequest;
import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;
import com.vocasia.payment.service.IOrderService;
import com.vocasia.payment.service.IPaymentService;
import com.vocasia.payment.util.MidtransHashUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Midtrans Controller", description = "Controller untuk midtrans")
public class MidtransController {

    private final Logger logger = LoggerFactory.getLogger(MidtransController.class);

    private final MidtransConfigProperties midtransConfigProperties;
    private final MidtransHashUtil midtransHashUtil;
    private final IPaymentService paymentService;
    private final IOrderService orderService;

    public MidtransController(MidtransConfigProperties midtransConfigProperties, MidtransHashUtil midtransHashUtil, IPaymentService paymentService, IOrderService orderService) {
        this.midtransConfigProperties = midtransConfigProperties;
        this.midtransHashUtil = midtransHashUtil;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    @PostMapping("/midtrans-callback")
    public ResponseEntity<ResponseDto> midtransCallback(@RequestBody MidtransCallbackRequest midtransCallbackRequest) {
        logger.debug(midtransCallbackRequest.toString());

        String serverKey = midtransConfigProperties.getServerKey();
        String orderId = midtransCallbackRequest.getOrderId();
        String statusCode = midtransCallbackRequest.getStatusCode();
        String grossAmount = midtransCallbackRequest.getGrossAmount();

        String hashedKey = MidtransHashUtil.generateMidtransHash(orderId, statusCode, grossAmount, serverKey);

        if (!hashedKey.equals(midtransCallbackRequest.getSignatureKey())) {
            logger.debug("Hashed key does not match signature key");

            return ResponseEntity.status(401).body(new ResponseDto(false, "Akses tidak diizinkan", null, "Anda tidak diizinkan mengakses resource ini!"));
        }

        Map<String, Object> response = new HashMap<>();

        Payment payment = paymentService.getByOrderNumber(midtransCallbackRequest.getOrderId());

        if (payment == null) {
            return ResponseEntity.status(404).body(new ResponseDto(false, "Payment not found", null, "Sorry but payment data is not found!"));
        }

        response.put("hashedKey", hashedKey);
        response.put("signatureKey", midtransCallbackRequest.getSignatureKey());
        response.put("key_match", hashedKey.equals(midtransCallbackRequest.getSignatureKey()));

        String transactionStatus = midtransCallbackRequest.getTransactionStatus();
        String paymentType = midtransCallbackRequest.getPaymentType();
        String fraudStatus = midtransCallbackRequest.getFraudStatus();

        String paymentStatus = "unknown";

        switch (transactionStatus) {
            case "capture":
                if ("credit_card".equals(paymentType)) {
                    if ("challenge".equals(fraudStatus)) {
                        paymentStatus = "pending";
                    } else {
                        paymentStatus = "success";
                    }
                }
                break;
            case "settlement":
                paymentStatus = "success";
                break;
            case "pending":
                paymentStatus = "pending";
                break;
            case "deny":
                paymentStatus = "failed";
                break;
            case "expire":
                paymentStatus = "expired";
                break;
            case "cancel":
                paymentStatus = "canceled";
                break;
            default:
                paymentStatus = "unknown";
                break;
        }

        paymentService.updatePaymentStatus(payment, paymentStatus);

        try {
            UpdateOrderPaymentStatus updateOrderPaymentStatus = new UpdateOrderPaymentStatus();

            updateOrderPaymentStatus.setStatus(paymentStatus);
            updateOrderPaymentStatus.setTransactionStatus(transactionStatus);

            OrderDto orderDto = orderService.updatePaymentStatus(payment.getOrderId(), updateOrderPaymentStatus);

            logger.debug("Order status updated: " + orderDto.getPaymentStatus());
        } catch (Exception e) {
            logger.error("Error updating payment status", e);
        }

        return ResponseEntity.ok(new ResponseDto(true, "Midtrans callback", response, null));
    }

}
