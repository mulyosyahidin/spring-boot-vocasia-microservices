package com.vocasia.instructor.dto.client.finance;

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

}