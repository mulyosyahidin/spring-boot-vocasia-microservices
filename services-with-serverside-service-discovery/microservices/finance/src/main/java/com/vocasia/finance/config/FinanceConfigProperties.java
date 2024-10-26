package com.vocasia.finance.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "finance")
@Getter
@Setter
public class FinanceConfigProperties {

    private BigDecimal percentPlatformFee;

}
