package com.vocasia.authentication.request.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserProfileRequest {

    @Email(message = "Email harus valid")
    @NotBlank(message = "Email harus diisi")
    private String email;

}
