package com.vocasia.instructor.controller.admin;

import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.authentication.UserDto;
import com.vocasia.instructor.entity.Instructor;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.mapper.InstructorMapper;
import com.vocasia.instructor.service.IAuthenticationService;
import com.vocasia.instructor.service.IInstructorService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class InstructorController {

    private final Logger logger = LoggerFactory.getLogger(InstructorController.class);

    private final IInstructorService instructorService;
    private final IAuthenticationService authenticationService;

    public InstructorController(IInstructorService instructorService, IAuthenticationService authenticationService) {
        this.instructorService = instructorService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/instructors")
    public ResponseEntity<ResponseDto> getAllInstructors(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                         @RequestParam(defaultValue = "1") int page) {
        logger.info("InstructorController.getAllInstructors called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit);

        Page<Instructor> instructors = instructorService.findAllByStatus("approved", paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        List<Map<String, Object>> instructorsData;

        try {
            instructorsData = instructors.getContent().stream().map(instructor -> {
                Map<String, Object> instructorData = new HashMap<>();

                UserDto userDto = authenticationService.findUserById(instructor.getUserId(), correlationId);

                instructorData.put("instructor", InstructorMapper.mapToDto(instructor));
                instructorData.put("user", userDto);

                return instructorData;
            }).toList();
        }
        catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        pagination.put("total_page", instructors.getTotalPages());
        pagination.put("per_page", instructors.getSize());
        pagination.put("current_page", instructors.getNumber() + 1);
        pagination.put("total_items", instructors.getTotalElements());

        response.put("data", instructorsData);
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil menampilkan data instruktur", response, null));
    }

    @GetMapping("/instructors/{instructorId}")
    public ResponseEntity<ResponseDto> getInstructorById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                         @PathVariable Long instructorId) {
        logger.info("InstructorController.getInstructorById called");

        Instructor instructor = instructorService.findById(instructorId);

        Map<String, Object> response = new HashMap<>();

        try {
            UserDto userDto = authenticationService.findUserById(instructor.getUserId(), correlationId);

            response.put("instructor", InstructorMapper.mapToDto(instructor));
            response.put("user", userDto);
        }
        catch (CustomFeignException e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(e.getHttpStatus())
                    .body(new ResponseDto(false, e.getMessage(), null, e.getErrors()));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);

            return ResponseEntity
                    .status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(false, e.getMessage(), null, null));
        }

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data instruktur", response, null));
    }

}
