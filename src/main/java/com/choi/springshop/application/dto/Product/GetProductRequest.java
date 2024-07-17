package com.choi.springshop.application.dto.Product;

import com.choi.springshop.domain.model.entity.Category;
import com.fasterxml.jackson.core.JsonToken;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetProductRequest {

    private Long id;

    private String name;

    private String description;

    private Float price;

    private String imageUrl;

    private Category category;

    private Integer stockQuantity;


}
