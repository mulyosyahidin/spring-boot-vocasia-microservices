package com.vocasia.authentication.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForgetPasswordRequest {

    @NotBlank(message = "Email is required")
    private String email;

}
