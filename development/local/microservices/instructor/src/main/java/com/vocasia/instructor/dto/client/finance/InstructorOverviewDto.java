package com.vocasia.instructor.dto.client.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InstructorOverviewDto {

    @JsonProperty("total_income")
    private double totalIncome;

    @JsonProperty("total_withdrawn")
    private double totalWithdrawn;

    @JsonProperty("total_platform_fee")
    private double totalPlatformFee;

    @JsonProperty("monthly_incomes")
    private List<MonthlyIncomeDto> monthlyIncomes;

}
