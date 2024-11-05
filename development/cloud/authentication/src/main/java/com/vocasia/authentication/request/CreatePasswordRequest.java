package com.vocasia.authentication.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePasswordRequest {

    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "New password is required")
    @JsonProperty("new_password")
    private String newPassword;

    @NotBlank(message = "Confirm new password is required")
    @JsonProperty("confirm_new_password")
    private String confirmNewPassword;

}
