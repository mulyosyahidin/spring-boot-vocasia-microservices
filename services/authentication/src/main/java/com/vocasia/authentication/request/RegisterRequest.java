package com.vocasia.authentication.request;

import com.vocasia.authentication.validation.EmailExists;
import com.vocasia.authentication.validation.UsernameExists;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Name harus diisi")
    private String name;

    @Email(message = "Email harus valid")
    @NotBlank(message = "Email harus diisi")
    @EmailExists
    private String email;

    @NotBlank(message = "Username harus diisi")
    @UsernameExists
    private String username;

    @NotBlank(message = "Password harus diisi")
    @Size(min = 6, message = "Password minimal 6 karakter")
    private String password;

    private String role;
}