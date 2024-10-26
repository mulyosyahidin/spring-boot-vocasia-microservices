package com.vocasia.finance.repository;

import com.vocasia.finance.entity.PlatformIncome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlatformIncomeRepository extends JpaRepository<PlatformIncome, Long> {

    PlatformIncome findByOrderId(Long orderId);
    List<PlatformIncome> findAllByOrderId(Long orderId);

}
