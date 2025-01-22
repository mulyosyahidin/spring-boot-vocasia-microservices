package com.vocasia.payment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "additional_fee", nullable = false)
    private Double additionalFee;

    @Column(name = "total_payment", nullable = false)
    private Double totalPayment;

    @Column(name = "snap_token", nullable = false)
    private String snapToken;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "payment_expire_at", nullable = false)
    private LocalDateTime paymentExpireAt;

}
