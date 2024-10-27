package com.vocasia.finance.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "platform_balances")
@ToString
public class PlatformBalance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "current_balance", nullable = false)
    private Double currentBalance;

    @Column(name = "total_income", nullable = false)
    private Double totalIncome;

    @Column(name = "total_pending_withdrawal")
    private Double totalPendingWithdrawal;

    @Column(name = "total_withdrawn", nullable = false)
    private Double totalWithdrawn;

    @Column(name = "last_history_id")
    private Long lastHistoryId;

}

