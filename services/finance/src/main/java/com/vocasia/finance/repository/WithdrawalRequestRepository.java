package com.vocasia.finance.repository;

import com.vocasia.finance.entity.WithdrawalRequest;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WithdrawalRequestRepository extends JpaRepository<WithdrawalRequest, Long> {

    List<WithdrawalRequest> findAllByInstructorId(Long instructorId);
    int countByStatus(String status);

    @Query("SELECT SUM(w.amount) FROM WithdrawalRequest w WHERE w.status = :status")
    Double sumAmountByStatus(@Param("status") String status);

    List<WithdrawalRequest> findAllByStatus(String status);

}
