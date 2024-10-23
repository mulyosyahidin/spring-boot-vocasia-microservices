package com.vocasia.finance.controller.instructor;

import com.vocasia.finance.dto.ResponseDto;
import com.vocasia.finance.dto.data.MonthlyIncomeDto;
import com.vocasia.finance.entity.InstructorBalance;
import com.vocasia.finance.service.IInstructorBalanceService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;

@RestController
@RequestMapping("/api/instructor")
public class OverviewController {

    private final Logger logger = LoggerFactory.getLogger(OverviewController.class);

    private final IInstructorBalanceService instructorBalanceService;

    public OverviewController(IInstructorBalanceService iInstructorBalanceService) {
        this.instructorBalanceService = iInstructorBalanceService;
    }

    @GetMapping("/overview")
    public ResponseEntity<ResponseDto> getInstructorOverview(@RequestHeader("vocasia-correlation-id") String correlationId,
                                                             @RequestHeader("X-INSTRUCTOR-ID") Long instructorId) {
        logger.info("OverviewController.getInstructorOverview called");

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> overview = new HashMap<>();

        InstructorBalance instructorBalance = instructorBalanceService.findByInstructorId(instructorId);

        double totalIncome = instructorBalance.getTotalIncome();
        double totalWithdrawn = instructorBalance.getTotalWithdrawn();
        double totalPlatformFee = instructorBalance.getTotalPlatformFee();

        overview.put("total_income", totalIncome);
        overview.put("total_withdrawn", totalWithdrawn);
        overview.put("total_platform_fee", totalPlatformFee);

        LocalDateTime now = LocalDateTime.now();
        List<MonthlyIncomeDto> monthlyIncomes = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            LocalDateTime date = now.minusMonths(i);
            String monthName = date.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("id"));

            double monthlyIncome = instructorBalanceService.sumTotalIncomeByInstructorIdAndMonthAndYear(
                    instructorId,
                    date.getMonthValue(),
                    date.getYear()
            );

            monthlyIncomes.add(new MonthlyIncomeDto(date.getMonthValue(), date.getYear(), monthName, monthlyIncome));
        }

        Collections.reverse(monthlyIncomes);

        response.put("monthly_incomes", monthlyIncomes);
        response.put("overview", overview);

        return ResponseEntity
                .status(HttpStatus.SC_OK)
                .body(new ResponseDto(true, "Berhasil mendapatkan data overview instruktur", response, null));
    }

}
