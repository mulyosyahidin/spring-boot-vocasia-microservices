package com.vocasia.qna;

import com.vocasia.qna.config.AppConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = { AppConfigProperties.class })
@EnableFeignClients
@EnableJpaAuditing
@EnableCaching
public class QnAServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QnAServiceApplication.class, args);
	}

}
