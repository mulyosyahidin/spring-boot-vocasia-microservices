package com.vocasia.payment.service.impl;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.httpclient.error.MidtransError;
import com.midtrans.service.MidtransSnapApi;
import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.request.CreateOrderPaymentRequest;
import com.vocasia.payment.service.IMidtransPaymentService;
import com.vocasia.payment.util.StringUtil;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MidtransPaymentServiceImpl implements IMidtransPaymentService {

    private final Logger logger = LoggerFactory.getLogger(MidtransPaymentServiceImpl.class);

    private final MidtransConfigProperties midtransConfigProperties;

    @Override
    public String requestSnapToken(CreateOrderPaymentRequest createOrderPaymentRequest) {
        try {
            Double additionalFee = createOrderPaymentRequest.getAdditionalFee();
            Double totalPrice = createOrderPaymentRequest.getTotalPrice();

            String grossAmount = String.valueOf(totalPrice + additionalFee);

            MidtransSnapApi snapApi = new ConfigFactory(
                    new Config(
                            midtransConfigProperties.getServerKey(),
                            midtransConfigProperties.getClientKey(),
                            midtransConfigProperties.isProduction())).getSnapApi();

            Map<String, Object> params = new HashMap<>();

            Map<String, String> transactionDetails = new HashMap<>();
            transactionDetails.put("order_id", createOrderPaymentRequest.getOrderNumber());
            transactionDetails.put("gross_amount", grossAmount);
            params.put("transaction_details", transactionDetails);

            List<Map<String, Object>> itemDetails = getMaps(createOrderPaymentRequest, additionalFee);
            params.put("item_details", itemDetails);

            Map<String, Object> customerDetail = new HashMap<>();

            customerDetail.put("first_name", createOrderPaymentRequest.getCustomer().getFirstName());
            customerDetail.put("last_name", createOrderPaymentRequest.getCustomer().getLastName());
            customerDetail.put("email", createOrderPaymentRequest.getCustomer().getEmail());
            customerDetail.put("phone", "-");
            params.put("customer_details", customerDetail);

            return snapApi.createTransactionToken(params);
        } catch (MidtransError e) {
            logger.error(e.getMessage(), e);

            throw new RuntimeException("Gagal membuat transaksi: " + e.getMessage());
        }
    }

    @NotNull
    private static List<Map<String, Object>> getMaps(CreateOrderPaymentRequest createOrderPaymentRequest, Double additionalFee) {
        List<Map<String, Object>> itemDetails = new ArrayList<>();

        if (createOrderPaymentRequest.getAdditionalFee() != null) {
            Map<String, Object> additionalFeeItemDetail = new HashMap<>();

            additionalFeeItemDetail.put("id", "additional_fee");
            additionalFeeItemDetail.put("quantity", 1);
            additionalFeeItemDetail.put("price", additionalFee);
            additionalFeeItemDetail.put("name", "[Platform Additional Fee]");

            itemDetails.add(additionalFeeItemDetail);
        }

        List<CreateOrderPaymentRequest.Item> items = createOrderPaymentRequest.getItems();
        for (CreateOrderPaymentRequest.Item item : items) {
            Map<String, Object> itemDetail = new HashMap<>();
            Double finalPrice = item.getCoursePrice() - item.getCourseDiscount();

            itemDetail.put("id", item.getCourseId());
            itemDetail.put("quantity", 1);
            itemDetail.put("price", finalPrice);
            itemDetail.put("name", StringUtil.cutString(item.getCourseTitle(), 20));

            itemDetails.add(itemDetail);
        }

        return itemDetails;
    }

}
