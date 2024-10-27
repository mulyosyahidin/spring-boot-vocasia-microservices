package com.vocasia.authentication.controller.admin;

import com.vocasia.authentication.dto.ResponseDto;
import com.vocasia.authentication.entity.User;
import com.vocasia.authentication.mapper.UserMapper;
import com.vocasia.authentication.service.IUserService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final IUserService userService;

    public StudentController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/students")
    public ResponseEntity<ResponseDto> getAllStudents(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @RequestParam(defaultValue = "1") int page) {
        logger.info("StudentController.getAllStudents called");

        page = page < 1 ? 1 : page - 1;
        int limit = 10;

        Pageable paging = PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC, "createdAt"));

        Page<User> studentUsers = userService.findAllByRole("student", paging);

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> pagination = new HashMap<>();

        pagination.put("total_page", studentUsers.getTotalPages());
        pagination.put("per_page", studentUsers.getSize());
        pagination.put("current_page", studentUsers.getNumber() + 1);
        pagination.put("total_items", studentUsers.getTotalElements());

        response.put("data", studentUsers.getContent().stream().map(UserMapper::mapToDto));
        response.put("pagination", pagination);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<ResponseDto> getStudentById(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                      @PathVariable Long id) {
        logger.info("StudentController.getStudentById called");

        User studentUser = userService.findById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("user", UserMapper.mapToDto(studentUser));

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data siswa", response, null));
    }

}
