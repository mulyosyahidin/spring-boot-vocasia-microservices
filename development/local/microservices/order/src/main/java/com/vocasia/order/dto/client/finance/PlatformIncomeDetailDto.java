package com.vocasia.order.dto.client.finance;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vocasia.order.dto.client.course.CourseDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class PlatformIncomeDetailDto implements Serializable {

    private CourseDto course;

    @JsonProperty("platform_income")
    private PlatformIncomeDto platformIncome;

}
