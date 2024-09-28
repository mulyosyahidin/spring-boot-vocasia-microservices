package com.vocasia.instructor.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.DefaultValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name harus diisi")
    private String name;

    @Email(message = "Email harus valid")
    @NotBlank(message = "Email harus diisi")
    private String email;

    @NotBlank(message = "Username harus diisi")
    private String username;

    @NotBlank(message = "Password harus diisi")
    @Size(min = 6, message = "Password minimal 6 karakter")
    private String password;

    @JsonProperty("phone_number")
    @NotBlank(message = "No. HP harus diisi")
    private String phoneNumber;

    private String summary;

    private String role;

    public RegisterRequest() {
        this.role = "instructor";
    }

}
