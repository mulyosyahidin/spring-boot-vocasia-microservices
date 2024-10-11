package com.vocasia.instructor.controller;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.IInstructorService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Validated
public class RegisterController {

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final IInstructorService instructorService;
    private final IAuthenticationService authenticationService;

    public RegisterController(IInstructorService iInstructorService, IAuthenticationService iAuthenticationService) {
        this.instructorService = iInstructorService;
        this.authenticationService = iAuthenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                @Valid @RequestBody RegisterRequest registerRequest) {
        logger.debug("RegisterController.register called");

        try {
            UserDto registeredUser = authenticationService.registerNewUser(registerRequest, correlationId);
            Instructor registeredInstructor = instructorService.registerNewInstructor(registeredUser.getId(), registerRequest);

            Map<String, Object> response = new HashMap<>();

            response.put("user", registeredUser);
            response.put("instructor", InstructorMapper.mapToDto(registeredInstructor));

            return ResponseEntity.ok(new ResponseDto(true, "Berhasil melakukan pendaftaran", response, null));
        } catch (CustomFeignException e) {
            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Internal server error", null, null));
        }
    }

}
