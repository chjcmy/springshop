package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.application.dto.Product.GetProductResponse;
import com.choi.springshop.application.service.Product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<GetProductResponse> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Long categoryId,
            Pageable pageable) {
        return productService.getProducts(name, minPrice, maxPrice, categoryId, pageable);
    }
}
