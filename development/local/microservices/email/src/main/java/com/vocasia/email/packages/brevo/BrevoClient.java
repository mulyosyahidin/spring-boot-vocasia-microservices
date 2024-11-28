package com.vocasia.email.packages.brevo;

import com.vocasia.email.config.BrevoConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.SendSmtpEmail;
import sibModel.SendSmtpEmailSender;
import sibModel.SendSmtpEmailTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Component
public class BrevoClient {

    private final Logger logger = LoggerFactory.getLogger(BrevoClient.class);

    private final BrevoConfigProperties brevoConfigProperties;

    public BrevoClient(BrevoConfigProperties brevoConfigProperties) {
        this.brevoConfigProperties = brevoConfigProperties;
    }

    public void sendEmail(String templateName, String subject, String to, Map<String, Object> data) {
        ApiClient apiClient = new ApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) apiClient.getAuthentication("api-key");
        apiKeyAuth.setApiKey(brevoConfigProperties.getApiKey());

        TransactionalEmailsApi emailApi = new TransactionalEmailsApi(apiClient);

        SendSmtpEmail email = new SendSmtpEmail()
                .sender(new SendSmtpEmailSender()
                        .name(brevoConfigProperties.getSenderName())
                        .email(brevoConfigProperties.getSenderEmail()))
                .to(List.of(new SendSmtpEmailTo().email(to)))
                .subject(subject)
                .htmlContent(generateEmailContent(templateName, data));

        try {
            emailApi.sendTransacEmail(email);

            logger.info("Email sent successfully to {}", to);
        } catch (ApiException e) {
            logger.error("Failed to send email: {}", e.getMessage());
        }
    }

    private String generateEmailContent(String templateName, Map<String, Object> data) {
        String content = "";

        try {
            String templatePath = "classpath:templates/email/"+ templateName +".html";
            content = new String(Files.readAllBytes(Paths.get(ResourceUtils.getFile(templatePath).toURI())));

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String placeholder = "{{" + entry.getKey() + "}}";
                content = content.replace(placeholder, entry.getValue().toString());
            }
        }
        catch (IOException e) {
            logger.error("Error reading email template: {}", e.getMessage());
        }

        return content;
    }

}
