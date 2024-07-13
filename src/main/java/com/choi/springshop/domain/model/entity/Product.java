package com.choi.springshop.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;
    private String imageUrl;

    @Column(name = "stock")
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}

