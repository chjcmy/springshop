package com.choi.springshop.application.dto.Product;

import lombok.Data;

@Data
public class GetProductResponse {

    public GetProductResponse(Long id, String name, String imageUrl, Float price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;

    }

    private Long id;

    private String name;

    private String imageUrl;

    private Float price;

}
