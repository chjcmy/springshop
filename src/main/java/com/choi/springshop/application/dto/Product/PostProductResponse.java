package com.choi.springshop.application.dto.Product;

import com.choi.springshop.domain.model.entity.Category;
import lombok.Data;

@Data
public class PostProductResponse {

    private String name;

    private String description;

    private Integer price;

    private Integer stockQuantity;

    private String imageUrl;

    private Category category;

}
