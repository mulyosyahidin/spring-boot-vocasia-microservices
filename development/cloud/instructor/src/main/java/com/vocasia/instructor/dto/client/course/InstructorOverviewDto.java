package com.vocasia.instructor.dto.client.course;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InstructorOverviewDto {

    private int draft;
    private int published;
    private int total;

}
