package com.vocasia.finance.repository;

import com.vocasia.finance.entity.PlatformBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlatformBalanceHistoryRepository extends JpaRepository<PlatformBalanceHistory, Long> {
}
