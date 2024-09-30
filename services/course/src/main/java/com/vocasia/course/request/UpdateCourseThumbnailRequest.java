package com.vocasia.course.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UpdateCourseThumbnailRequest {

    @NotNull(message = "File gambar harus diisi")
    private MultipartFile picture;

}
