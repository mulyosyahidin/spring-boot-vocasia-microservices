package com.vocasia.finance.controller;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.mapper.InstructorIncomeMapper;
import com.vocasia.finance.request.NewInstructorIncomeRequest;
import com.vocasia.finance.service.IInstructorIncomeService;
import jakarta.validation.Valid;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/instructor-income")
public class InstructorIncomeController {

    private final Logger logger = LoggerFactory.getLogger(InstructorIncomeController.class);

    private final IInstructorIncomeService instructorIncomeService;

    public InstructorIncomeController(IInstructorIncomeService iInstructorIncomeService) {
        this.instructorIncomeService = iInstructorIncomeService;
    }

    @PostMapping("/store")
    public ResponseEntity<ResponseDto> createInstructorIncome(@RequestHeader("vocasia-correlation-id") String correlationId,
                                            @Valid @RequestBody NewInstructorIncomeRequest newInstructorIncomeRequest) {
        logger.info("InstructorIncomeController.createInstructorIncome called");

        InstructorIncome savedInstructorIncome = instructorIncomeService.save(newInstructorIncomeRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_income", InstructorIncomeMapper.mapToDto(savedInstructorIncome));

        return ResponseEntity
                .status(HttpStatus.SC_CREATED)
                .body(new ResponseDto(true, "Berhasil menyimpan data pendapatan instruktur", response, null));
    }

}
