package com.vocasia.finance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "withdrawal_process")
@ToString
public class WithdrawalProcess extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "withdrawal_request_id")
    private Long withdrawalRequestId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "proof_document")
    private String proofDocument;

    private String note;

    @Column(name = "status", nullable = false)
    private String status;

}
