package com.vocasia.instructor.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vocasia.instructor.dto.ResponseDto;
import com.vocasia.instructor.dto.client.finance.InstructorOverviewDto;
import com.vocasia.instructor.dto.client.finance.MonthlyIncomeDto;
import com.vocasia.instructor.exception.CustomFeignException;
import com.vocasia.instructor.service.IFinanceService;
import com.vocasia.instructor.service.client.FinanceFeignClient;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FinanceServiceImpl implements IFinanceService {

    private final Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

    private FinanceFeignClient financeFeignClient;

    @Override
    public InstructorOverviewDto getInstructorOverview(String correlationId, Long instructorId) {
        try {
            ResponseEntity<ResponseDto> financeFeignClientInstructorCourseOverviewResponseEntity = financeFeignClient.getInstructorOverview(correlationId, instructorId);
            ResponseDto responseBody = financeFeignClientInstructorCourseOverviewResponseEntity.getBody();

            assert responseBody != null;
            Map<String, Object> data = (Map<String, Object>) responseBody.getData();

            Map<String, Object> overview = data != null ? (Map<String, Object>) data.get("overview") : null;
            List<Map<String, Object>> monthlyIncomes = data != null ? (List<Map<String, Object>>) data.get("monthly_incomes") : null;

            logger.debug("FinanceServiceImpl.getInstructorOverview() $overview:: {}", overview);
            logger.debug("FinanceServiceImpl.getInstructorOverview() $monthlyIncomes:: {}", monthlyIncomes);

            com.vocasia.instructor.dto.client.finance.InstructorOverviewDto instructorOverviewDto = new com.vocasia.instructor.dto.client.finance.InstructorOverviewDto();
            if (overview != null) {
                instructorOverviewDto.setTotalIncome(Double.parseDouble(overview.get("total_income").toString()));
                instructorOverviewDto.setTotalWithdrawn(Double.parseDouble(overview.get("total_withdrawn").toString()));
                instructorOverviewDto.setTotalPlatformFee(Double.parseDouble(overview.get("total_platform_fee").toString()));
            }

            List<MonthlyIncomeDto> monthlyIncomesDto = new ArrayList<>();
            if (monthlyIncomes != null) {
                for (Map<String, Object> monthlyIncome : monthlyIncomes) {
                    MonthlyIncomeDto monthlyIncomeDto = new MonthlyIncomeDto();

                    monthlyIncomeDto.setMonth(Integer.parseInt(monthlyIncome.get("month").toString()));
                    monthlyIncomeDto.setMonthName(monthlyIncome.get("month_name").toString());
                    monthlyIncomeDto.setYear(Integer.parseInt(monthlyIncome.get("year").toString()));
                    monthlyIncomeDto.setIncome(Double.parseDouble(monthlyIncome.get("income").toString()));

                    monthlyIncomesDto.add(monthlyIncomeDto);
                }

                instructorOverviewDto.setMonthlyIncomes(monthlyIncomesDto);
            }

            return instructorOverviewDto;
        }
        catch (FeignException e) {
            throw new CustomFeignException(e, new ObjectMapper());
        }
    }

}
