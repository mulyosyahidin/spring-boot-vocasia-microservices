package com.vocasia.payment;

import com.vocasia.payment.config.AppConfigProperties;
import com.vocasia.payment.config.MidtransConfigProperties;
import com.vocasia.payment.config.PaymentConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(value = {AppConfigProperties.class, MidtransConfigProperties.class, PaymentConfigProperties.class})
@EnableFeignClients
@EnableJpaAuditing
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
