package com.choi.springshop.application.dto.Product;

import lombok.Data;

@Data
public class GetProductResponse {

    public GetProductResponse(Long id, String name, String description, String imageUrl, Float price, Integer stock, Long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private Float price;

    private Integer stock;

    private Long categoryId;


}
