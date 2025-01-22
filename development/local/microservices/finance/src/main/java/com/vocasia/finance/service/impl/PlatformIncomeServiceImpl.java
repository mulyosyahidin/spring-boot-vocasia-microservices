package com.vocasia.finance.service.impl;

import com.vocasia.finance.config.FinanceConfigProperties;
import com.vocasia.finance.entity.PlatformIncome;
import com.vocasia.finance.repository.PlatformIncomeRepository;
import com.vocasia.finance.request.NewPlatformIncomeRequest;
import com.vocasia.finance.service.IPlatformIncomeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PlatformIncomeServiceImpl implements IPlatformIncomeService {

    private FinanceConfigProperties financeConfigProperties;
    private PlatformIncomeRepository platformIncomeRepository;

    @Override
    public PlatformIncome save(NewPlatformIncomeRequest newPlatformIncomeRequest) {
        PlatformIncome platformIncome = new PlatformIncome();

        BigDecimal percentPlatformFee = financeConfigProperties.getPercentPlatformFee();
        Double totalPlatformIncome = newPlatformIncomeRequest.getTotalUserPayment() * (percentPlatformFee.doubleValue() / 100);

        platformIncome.setInstructorId(newPlatformIncomeRequest.getInstructorId());
        platformIncome.setOrderId(newPlatformIncomeRequest.getOrderId());
        platformIncome.setCourseId(newPlatformIncomeRequest.getCourseId());
        platformIncome.setTotalUserPayment(newPlatformIncomeRequest.getTotalUserPayment());
        platformIncome.setPlatformFeeInPercent(percentPlatformFee);
        platformIncome.setTotalPlatformIncome(totalPlatformIncome);
        platformIncome.setRemarks(newPlatformIncomeRequest.getRemarks());

        return platformIncomeRepository.save(platformIncome);
    }

    @Override
    public List<PlatformIncome> findAllByOrderId(Long orderId) {
        return platformIncomeRepository.findAllByOrderId(orderId);
    }

}
