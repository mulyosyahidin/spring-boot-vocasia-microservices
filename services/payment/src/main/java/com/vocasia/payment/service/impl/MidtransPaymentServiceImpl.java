package com.vocasia.payment.service.impl;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.Midtrans;
import com.midtrans.httpclient.SnapApi;
import com.midtrans.httpclient.error.MidtransError;
import com.midtrans.service.MidtransSnapApi;
import com.midtrans.service.impl.MidtransSnapApiImpl;
import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.service.IMidtransPaymentService;
import com.vocasia.payment.request.CreateOrderPaymentRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class MidtransPaymentServiceImpl implements IMidtransPaymentService {

    private final MidtransConfigProperties midtransConfigProperties;

    @Override
    public String requestSnapToken(CreateOrderPaymentRequest createOrderPaymentRequest) {
        try {
            MidtransSnapApi snapApi = new ConfigFactory(new Config(midtransConfigProperties.getServerKey(), midtransConfigProperties.getClientKey(), midtransConfigProperties.isProduction())).getSnapApi();

            Map<String, Object> params = new HashMap<>();

            Map<String, String> transactionDetails = new HashMap<>();
            transactionDetails.put("order_id", createOrderPaymentRequest.getOrderNumber());
            transactionDetails.put("gross_amount", createOrderPaymentRequest.getTotalPrice().toString());

            params.put("transaction_details", transactionDetails);

            String transactionToken = snapApi.createTransactionToken(params);

            return transactionToken;
        } catch (MidtransError e) {
            e.printStackTrace();

            throw new RuntimeException("Gagal membuat transaksi: " + e.getMessage());
        }
    }

}
