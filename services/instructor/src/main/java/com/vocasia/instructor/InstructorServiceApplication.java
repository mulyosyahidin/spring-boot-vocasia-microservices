package com.vocasia.instructor;

import com.vocasia.instructor.config.AppConfigProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = {AppConfigProperties.class})
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "Instructor Service API",
				version = "1.0",
				description = "Dokumentasi API untuk Instructor Service"
		)
)
public class InstructorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstructorServiceApplication.class, args);
	}

}
