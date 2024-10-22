package com.vocasia.authentication;

import com.vocasia.authentication.config.AppConfigProperties;
import com.vocasia.authentication.config.AwsConfigProperties;
import com.vocasia.authentication.config.DefaultAdminConfig;
import com.vocasia.authentication.config.KeycloakConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppConfigProperties.class, KeycloakConfig.class, DefaultAdminConfig.class, AwsConfigProperties.class})
@EnableFeignClients
@EnableJpaAuditing
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
