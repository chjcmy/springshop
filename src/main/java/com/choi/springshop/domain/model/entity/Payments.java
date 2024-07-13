package com.choi.springshop.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Getter @Setter
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;


    public Payments() {} // JPA를 위한 기본 생성자

    public Payments(Order order, String paymentMethod, BigDecimal amount, String transactionId) {
        this.order = order;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", orderId=" + order.getId() +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
