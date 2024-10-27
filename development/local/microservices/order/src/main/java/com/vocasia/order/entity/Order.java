package com.vocasia.order.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = true)
    private Long userId;

    @Column(name = "order_number", nullable = true)
    private String orderNumber;

    @Column(name = "total_items", nullable = false)
    private int totalItems;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(name = "total_discount", nullable = false)
    private double totalDiscount;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

}

