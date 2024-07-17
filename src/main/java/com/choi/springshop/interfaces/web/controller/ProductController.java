package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.application.dto.Product.GetProductListRequest;
import com.choi.springshop.application.dto.Product.GetProductRequest;
import com.choi.springshop.application.dto.Product.PostProductResponse;
import com.choi.springshop.application.dto.Product.PutProductResponse;
import com.choi.springshop.application.service.Product.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<GetProductListRequest> getProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Float minPrice,
            @RequestParam(required = false) Float maxPrice,
            @RequestParam(required = false) Long categoryId,
            Pageable pageable) {
        return productService.getProducts(name, minPrice, maxPrice, categoryId, pageable);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GetProductRequest> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetProductRequest createProduct(@RequestBody @Valid PostProductResponse postProductResponse) {
        return productService.createProduct(postProductResponse);
    }

    @PutMapping("/{id}")
    public GetProductRequest updateProduct(@PathVariable Long id, @RequestBody @Valid PutProductResponse putProductResponse) {
        return productService.updateProduct(id, putProductResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
