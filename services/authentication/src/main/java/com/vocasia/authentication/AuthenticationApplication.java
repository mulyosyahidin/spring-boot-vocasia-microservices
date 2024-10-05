package com.vocasia.authentication;

import com.vocasia.authentication.config.AppConfigProperties;
import com.vocasia.authentication.config.AwsConfigProperties;
import com.vocasia.authentication.config.DefaultAdminConfig;
import com.vocasia.authentication.config.KeycloackConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfigProperties.class, KeycloackConfig.class, DefaultAdminConfig.class, AwsConfigProperties.class})
@EnableFeignClients
@EnableJpaAuditing
@OpenAPIDefinition(
		info = @Info(
				title = "Authentication Service API",
				version = "1.0",
				description = "Dokumentasi API untuk Authentication Service"
		)
)
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
