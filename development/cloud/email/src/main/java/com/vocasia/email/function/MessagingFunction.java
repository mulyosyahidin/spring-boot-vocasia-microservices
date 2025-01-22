package com.vocasia.email.function;

import com.vocasia.email.dto.EmailSendingDto;
import com.vocasia.email.packages.brevo.BrevoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessagingFunction {

    private final Logger logger = LoggerFactory.getLogger(MessagingFunction.class);

    private final BrevoClient brevoClient;

    public MessagingFunction(BrevoClient brevoClient) {
        this.brevoClient = brevoClient;
    }

    @Bean
    public Function<EmailSendingDto, EmailSendingDto> email() {
        return emailSendingDto -> {
            logger.info("Sending {} email to {} with subject {}", emailSendingDto.type(), emailSendingDto.to(), emailSendingDto.subject());

//            brevoClient.sendEmail(emailSendingDto.type(), emailSendingDto.subject(), emailSendingDto.to(), emailSendingDto.data());

            return emailSendingDto;
        };
    }

}
