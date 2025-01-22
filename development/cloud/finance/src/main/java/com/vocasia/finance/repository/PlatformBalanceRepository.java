package com.vocasia.finance.repository;

import com.vocasia.finance.entity.PlatformBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformBalanceRepository extends JpaRepository<PlatformBalance, Long> {
}
