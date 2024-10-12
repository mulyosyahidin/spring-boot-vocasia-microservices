package com.vocasia.payment.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment")
@Getter
@Setter
public class PaymentConfigProperties {

    private Double additionalFee;

}
