package com.vocasia.instructor.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateProfileRequest {

    @JsonProperty("user_id")
    private Long userId;

    @NotBlank(message = "Nama harus diisi")
    private String name;

    @NotBlank(message = "Email harus diisi")
    private String email;

    private String password;

    @NotBlank(message = "Biodata harus diisi")
    private String summary;

    @JsonProperty("profile_picture")
    private MultipartFile profilePicture;

    @NotBlank(message = "No. HP harus diisi")
    @JsonProperty("phone_number")
    private String phoneNumber;

}
