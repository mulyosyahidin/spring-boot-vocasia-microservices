package com.vocasia.authentication.controller;

import com.vocasia.authentication.config.AppConfigProperties;
import com.vocasia.authentication.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "Info Controller", description = "Controller untuk informasi aplikasi")
public class InfoController {

    private static final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @Autowired
    private AppConfigProperties appConfigProperties;

    @Operation(
            summary = "Selamat Datang!",
            description = "Menampilkan pesan selamat datang"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Pesan selamat datang berhasil diambil",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/welcome")
    public ResponseDto getWelcome() {
        return new ResponseDto(true, "Selamat Datang di Authentication Service", null, null);
    }

    @Operation(
            summary = "Build Information",
            description = "Menampilkan informasi nama, versi, dan lingkungan aplikasi yang diambil dari config-server"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Informasi build-info berhasil diambil",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/build-info")
    public ResponseDto getBuildInfo() {
        var data = new Object() {
            public final String name = appConfigProperties.getName();
            public final String version = appConfigProperties.getVersion();
            public final String environment = appConfigProperties.getEnvironment();
            public final Object java = new Object() {
                public final String version = System.getProperty("java.version");
                public final String vendor = System.getProperty("java.vendor");
                public final String vmName = System.getProperty("java.vm.name");
                public final String vmVersion = System.getProperty("java.vm.version");
                public final String sdkPath = System.getProperty("java.home");
            };
        };

        return new ResponseDto(true, "Berhasil mendapatkan informasi build-info", data, null);
    }

}
