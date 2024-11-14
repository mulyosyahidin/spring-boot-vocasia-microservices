package com.vocasia.payment.controller;

import com.vocasia.payment.config.AppConfigProperties;
import com.vocasia.payment.dto.ResponseDto;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    @Autowired
    private AppConfigProperties appConfigProperties;

    @GetMapping("/welcome")
    public ResponseEntity<ResponseDto> getWelcome() {
        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Selamat Datang di " + appConfigProperties.getName(), null, null));
    }

    @GetMapping("/build-info")
    public ResponseEntity<ResponseDto> getBuildInfo() {
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

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan informasi build-info", data, null));
    }

}