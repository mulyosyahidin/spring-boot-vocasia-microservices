package com.vocasia.authentication.controller.admin;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.service.IUserService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class OverviewController {

    private final Logger logger = LoggerFactory.getLogger(OverviewController.class);

    private final IUserService userService;

    public OverviewController(IUserService iUserService) {
        this.userService = iUserService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getOverview() {
        logger.info("OverviewController.getOverview called");

        Map<String, Object> response = new HashMap<>();
        response.put("total_users", userService.count() - 1);
        response.put("total_students", userService.countByRole("student"));
        response.put("total_instructors", userService.countByRole("instructor"));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data ringkasan", response, null));
    }
}
