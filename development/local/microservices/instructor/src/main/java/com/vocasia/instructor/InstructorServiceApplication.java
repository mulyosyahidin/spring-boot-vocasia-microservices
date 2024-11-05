package com.vocasia.instructor;

import com.vocasia.instructor.config.AppConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {AppConfigProperties.class})
@EnableJpaAuditing
@EnableCaching
public class InstructorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstructorServiceApplication.class, args);
	}

}
