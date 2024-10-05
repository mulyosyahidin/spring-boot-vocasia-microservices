package com.vocasia.payment.controller;

import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.dto.ResponseDto;
import com.vocasia.payment.entity.Payment;
import com.vocasia.payment.mapper.PaymentMapper;
import com.vocasia.payment.service.IMidtransPaymentService;
import com.vocasia.payment.request.CreateOrderPaymentRequest;
import com.vocasia.payment.service.IPaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
@Tag(name = "Payment Controller", description = "Controller untuk pembayaran")
public class PaymentController {

    private final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private final IPaymentService paymentService;
    private final IMidtransPaymentService midtransPaymentService;
    private final MidtransConfigProperties midtransConfig;

    public PaymentController(IPaymentService paymentService, IMidtransPaymentService midtransPaymentService, MidtransConfigProperties midtransConfig) {
        this.paymentService = paymentService;
        this.midtransPaymentService = midtransPaymentService;
        this.midtransConfig = midtransConfig;
    }

    @PostMapping("/create-order-payment")
    public ResponseEntity<ResponseDto> createOrderPayment(@Valid @RequestBody CreateOrderPaymentRequest createOrderPaymentRequest) {
        Map<String, Object> response = new HashMap<>();
        logger.info(createOrderPaymentRequest.getTotalPrice().toString());

        try {
            Payment getExistingPayment = paymentService.getPaymentByOrderId(createOrderPaymentRequest.getOrderId());

            if (getExistingPayment == null) {
                String snapToken = midtransPaymentService.requestSnapToken(createOrderPaymentRequest);
                Payment payment = paymentService.createPayment(snapToken, createOrderPaymentRequest);

                response.put("payment", PaymentMapper.mapToDto(payment));
            }
            else {
                response.put("payment", PaymentMapper.mapToDto(getExistingPayment));
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(false, "Gagal membuat pembayaran order", null, e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal membuat pembayaran order", null, e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil membuat pembayaran order", response, null));
    }

    @GetMapping("/payment-data-by-order-id/{orderId}")
    public ResponseEntity<ResponseDto> getPaymentDataByOrderId(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Payment getExistingPayment = paymentService.getPaymentByOrderId(orderId);

            if (getExistingPayment != null) {
                response.put("payment", PaymentMapper.mapToDto(getExistingPayment));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(false, "Data pembayaran tidak ditemukan", null, null));
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(false, "Gagal mendapatkan data pembayaran", null, e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(false, "Gagal mendapatkan data pembayaran", null, e.getMessage()));
        }

        return ResponseEntity.ok(new ResponseDto(true, "Berhasil mendapatkan data pembayaran", response, null));
    }

}
