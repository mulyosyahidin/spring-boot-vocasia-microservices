package com.vocasia.payment.controller;

import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.exception.CustomFeignException;
import com.vocasia.payment.request.MidtransCallbackRequest;
import com.vocasia.payment.request.client.UpdateOrderPaymentStatus;
import com.vocasia.payment.service.IOrderService;
import com.vocasia.payment.service.IPaymentService;
import com.vocasia.payment.util.MidtransHashUtil;
import org.apache.http.HttpStatus;
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
public class MidtransController {

    private final Logger logger = LoggerFactory.getLogger(MidtransController.class);

    private final MidtransConfigProperties midtransConfigProperties;
    private final IPaymentService paymentService;
    private final IOrderService orderService;

    public MidtransController(MidtransConfigProperties midtransConfigProperties, IPaymentService iPaymentService, IOrderService iOrderService) {
        this.midtransConfigProperties = midtransConfigProperties;
        this.paymentService = iPaymentService;
        this.orderService = iOrderService;
    }

    @PostMapping("/midtrans-callback")
    public ResponseEntity<ResponseDto> midtransCallback(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                        @RequestBody MidtransCallbackRequest midtransCallbackRequest) {
        logger.debug("MidtransController.midtransCallback called");

        String serverKey = midtransConfigProperties.getServerKey();
        String orderId = midtransCallbackRequest.getOrderId();
        String statusCode = midtransCallbackRequest.getStatusCode();
        String grossAmount = midtransCallbackRequest.getGrossAmount();

        String hashedKey = MidtransHashUtil.generateMidtransHash(orderId, statusCode, grossAmount, serverKey);

        if (!hashedKey.equals(midtransCallbackRequest.getSignatureKey())) {
            logger.debug("Hashed key does not match signature key");

            return ResponseEntity
                    .status(HttpStatus.SC_UNAUTHORIZED)
                    .body(new ResponseDto(false, "Akses tidak diizinkan", null, "Anda tidak diizinkan mengakses resource ini!"));
        }

        Map<String, Object> response = new HashMap<>();

        Payment payment = paymentService.findByOrderNumber(midtransCallbackRequest.getOrderId());

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

            orderService.updatePaymentStatus(payment.getOrderId(), updateOrderPaymentStatus, correlationId);
        } catch (CustomFeignException e) {
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Midtrans callback", response, null));
    }

}
