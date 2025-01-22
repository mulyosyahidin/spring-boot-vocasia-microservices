package com.vocasia.finance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "instuctor_income")
@ToString
public class InstructorIncome extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "course_id", nullable = false)
    private Long courseId;

    @Column(name = "total_user_payment", nullable = false)
    private Double totalUserPayment;

    @Column(name = "platform_fee_in_percent", nullable = false)
    private BigDecimal platformFeeInPercent;

    @Column(name = "total_instructor_income", nullable = false)
    private Double totalInstructorIncome;

    @Column(name = "remarks")
    private String remarks;

}
