package com.choi.springshop.domain.model.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // DATETIME 타입 처리
    private Date orderDate;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String status;

}

