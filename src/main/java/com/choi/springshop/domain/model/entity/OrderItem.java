package com.choi.springshop.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // 주문 엔티티와 연결

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // 상품 엔티티와 연결

    @Column(nullable = false)
    private int quantity; // 구매 수량

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice; // 단가 (할인 적용 전 가격)

    @Column(name = "discount_rate")
    private double discountRate;  // 할인율 (0.1은 10% 할인 의미)

}
