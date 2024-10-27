package com.vocasia.finance.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "instructor_balances")
@ToString
public class InstructorBalance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor_id")
    private Long instructorId;

    @Column(name = "current_balance")
    private Double currentBalance;

    @Column(name = "total_income")
    private Double totalIncome;

    @Column(name = "total_pending_withdrawal")
    private Double totalPendingWithdrawal;

    @Column(name = "total_withdrawn")
    private Double totalWithdrawn;

    @Column(name = "total_platform_fee")
    private Double totalPlatformFee;

    @Column(name = "last_history_id")
    private Long lastHistoryId;

}
