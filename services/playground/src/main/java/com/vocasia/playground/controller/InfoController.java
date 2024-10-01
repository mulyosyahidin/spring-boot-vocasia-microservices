package com.vocasia.playground.controller;

import com.vocasia.playground.config.KeycloackConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);
    private final KeycloackConfig keycloackConfig;

    public InfoController(KeycloackConfig keycloackConfig) {
        this.keycloackConfig = keycloackConfig;
    }

    @GetMapping("/config")
    public String info() {
        return "FROM CONFIG: " + keycloackConfig.getServer();
    }

}
