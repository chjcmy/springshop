package com.choi.springshop.application.service.Product;

import com.choi.springshop.application.dto.Product.GetProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<GetProductResponse> getProducts(String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable);
}
