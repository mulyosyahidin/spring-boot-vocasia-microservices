package com.vocasia.instructor.controller;

import com.vocasia.instructor.dto.feign.UserDto;
import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.mapper.UserMapper;
import com.vocasia.instructor.request.RegisterRequest;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.IInstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Register Controller", description = "Controller untuk pendaftaran instruktur")
public class RegisterController {

    private final IInstructorService iInstructorService;
    private final IAuthenticationService iUserService;

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    public RegisterController(IInstructorService iInstructorService, IAuthenticationService iUserService) {
        this.iInstructorService = iInstructorService;
        this.iUserService = iUserService;
    }

    @Operation(
            summary = "Pendaftaran Instruktur",
            description = "Pendaftaran instruktur baru"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Instruktur berhasil didaftarkan"
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Data yang diberikan tidak valid"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Terjadi kesalahan tidak terduga"
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> register(@RequestHeader("vocasia-correlation-id")
                                                String correlationId, @Valid @RequestBody RegisterRequest registerRequest) {
        try {
            UserDto registeredUser = iUserService.registerNewUser(registerRequest, correlationId);
            Instructor registeredInstructor = iInstructorService.registerNewInstructor(registeredUser.getId(), registerRequest);

            Map<String, Object> response = new HashMap<>();

            response.put("user", UserMapper.mapUserToResponse(registeredUser));
            response.put("instructor", InstructorMapper.mapToDto(registeredInstructor));

            return ResponseEntity.ok(new ResponseDto(true, "Berhasil melakukan pendaftaran", response, null));
        } catch (CustomFeignException ex) {
            logger.error("Validation error: {}", ex.getErrors());

            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(new ResponseDto(false, "Validation error", null, ex.getErrors()));
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, "Internal server error", null, null));
        }
    }
}
