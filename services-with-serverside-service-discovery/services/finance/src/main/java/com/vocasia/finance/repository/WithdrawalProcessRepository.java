package com.vocasia.finance.repository;

import com.vocasia.finance.entity.WithdrawalProcess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalProcessRepository extends JpaRepository<WithdrawalProcess, Long> {

    WithdrawalProcess findByWithdrawalRequestId(Long id);

}
