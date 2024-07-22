package com.choi.springshop.application.dto.Product;

import com.choi.springshop.domain.model.entity.Category;
import lombok.Data;

@Data
public class PutProductResponse {

    private String name;

    private String description;

    private Integer price;

    private String imageUrl;

    private int stockQuantity;

    private Category category;
}
