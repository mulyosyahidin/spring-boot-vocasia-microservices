package com.vocasia.finance.service.impl;

import com.vocasia.finance.config.FinanceConfigProperties;
import com.vocasia.finance.entity.InstructorIncome;
import com.vocasia.finance.repository.InstructorIncomeRepository;
import com.vocasia.finance.request.NewInstructorIncomeRequest;
import com.vocasia.finance.service.IInstructorIncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class InstructorIncomeServiceImpl implements IInstructorIncomeService {

    private FinanceConfigProperties financeConfigProperties;
    private InstructorIncomeRepository instructorIncomeRepository;

    @Override
    public InstructorIncome save(NewInstructorIncomeRequest newInstructorIncomeRequest) {
        InstructorIncome instructorIncome = new InstructorIncome();

        BigDecimal percentPlatformFee = financeConfigProperties.getPercentPlatformFee();
        Double totalInstructorIncome = newInstructorIncomeRequest.getTotalUserPayment() - (newInstructorIncomeRequest.getTotalUserPayment() * (percentPlatformFee.doubleValue() / 100));

        instructorIncome.setInstructorId(newInstructorIncomeRequest.getInstructorId());
        instructorIncome.setOrderId(newInstructorIncomeRequest.getOrderId());
        instructorIncome.setCourseId(newInstructorIncomeRequest.getCourseId());
        instructorIncome.setTotalUserPayment(newInstructorIncomeRequest.getTotalUserPayment());
        instructorIncome.setPlatformFeeInPercent(percentPlatformFee);
        instructorIncome.setTotalInstructorIncome(totalInstructorIncome);
        instructorIncome.setRemarks(newInstructorIncomeRequest.getRemarks());

        return instructorIncomeRepository.save(instructorIncome);
    }

}
