package com.vocasia.finance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "instructor_balance_histories")
@ToString
public class InstructorBalanceHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;

    @Column(name = "transaction_type", length = 50, nullable = false)
    private String transactionType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "previous_balance")
    private Double previousBalance;

    @Column(name = "new_balance")
    private Double newBalance;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "reference_id")
    private Long referenceId;

    @Column(name = "reference_type", length = 50)
    private String referenceType;

    @Column(name = "transaction_status")
    private String transactionStatus;

    @Column(name = "remarks", length = 255)
    private String remarks;

}
