package com.choi.springshop.application.dto.Product;

import lombok.Data;

@Data
public class GetProductListRequest {

    private Long id;

    private String name;

    private String imageUrl;

    private Integer price;

}
