package com.vocasia.finance.controller.admin;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.client.course.CourseDto;
import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.mapper.InstructorIncomeMapper;
import com.vocasia.finance.service.ICourseService;
import com.vocasia.finance.service.IInstructorIncomeService;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminInstructorIncomeController {

    private final Logger logger = LoggerFactory.getLogger(AdminInstructorIncomeController.class);

    private final IInstructorIncomeService instructorIncomeService;
    private final ICourseService courseService;

    public AdminInstructorIncomeController(IInstructorIncomeService iInstructorIncomeService, ICourseService iCourseService) {
        this.instructorIncomeService = iInstructorIncomeService;
        this.courseService = iCourseService;
    }

    @GetMapping("/instructor-income/{orderId}")
    public ResponseEntity<ResponseDto> findInstructorIncomeByOrderId(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                                     @PathVariable("orderId") Long orderId) {
        logger.info("InstructorIncomeController.findInstructorIncomeByOrderId called");

        List<InstructorIncome> instructorIncomes = instructorIncomeService.findAllByOrderId(orderId);
        List<Map<String, Object>> instructorIncomesData = instructorIncomes.stream().map(instructorIncome -> {
            Map<String, Object> instructorIncomeData = new HashMap<>();

            CourseDto course = courseService.findById(instructorIncome.getCourseId(), correlationId);

            instructorIncomeData.put("instructor_income", InstructorIncomeMapper.mapToDto(instructorIncome));
            instructorIncomeData.put("course", course);

            return instructorIncomeData;
        }).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("instructor_incomes", instructorIncomesData);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data incomes instruktur", response, null));
    }
}
