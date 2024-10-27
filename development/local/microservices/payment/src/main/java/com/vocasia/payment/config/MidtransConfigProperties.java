package com.vocasia.payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "midtrans")
@Getter
@Setter
public class MidtransConfigProperties {

    private String merchantId;
    private String serverKey;
    private String clientKey;
    private boolean isProduction;

}