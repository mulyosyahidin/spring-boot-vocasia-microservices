package com.vocasia.authentication.dto.email;

import java.util.Map;

public record EmailSendingDto(String type, String subject, String to, Map<String, Object> data) {
}
