package com.vocasia.finance;

import com.vocasia.finance.config.AppConfigProperties;
import com.vocasia.finance.config.FinanceConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfigProperties.class, FinanceConfigProperties.class})
@EnableFeignClients
@EnableJpaAuditing
public class FinanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceServiceApplication.class, args);
	}

}
