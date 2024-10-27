package com.vocasia.finance.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MonthlyIncomeDto {

    private int month;
    private int year;

    @JsonProperty("month_name")
    private String monthName;

    private double income;

    public MonthlyIncomeDto(int month, int year, String monthName, double income) {
        this.month = month;
        this.year = year;
        this.monthName = monthName;
        this.income = income;
    }

}

