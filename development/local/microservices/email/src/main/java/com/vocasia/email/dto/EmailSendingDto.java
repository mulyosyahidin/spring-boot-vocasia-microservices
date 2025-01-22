package com.vocasia.email.dto;

import java.util.Map;

public record EmailSendingDto(String type, String subject, String to, Map<String, Object> data) {
}
