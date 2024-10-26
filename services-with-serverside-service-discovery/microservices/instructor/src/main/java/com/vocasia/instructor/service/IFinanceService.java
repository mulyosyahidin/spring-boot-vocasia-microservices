package com.vocasia.instructor.service;

import com.vocasia.instructor.dto.client.finance.InstructorOverviewDto;

public interface IFinanceService {

    InstructorOverviewDto getInstructorOverview(String correlationId, Long instructorId);

}
