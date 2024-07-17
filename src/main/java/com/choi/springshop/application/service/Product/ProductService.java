package com.choi.springshop.application.service.Product;

import com.choi.springshop.application.dto.Product.GetProductListRequest;
import com.choi.springshop.application.dto.Product.GetProductRequest;
import com.choi.springshop.application.dto.Product.PostProductResponse;
import com.choi.springshop.application.dto.Product.PutProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Page<GetProductListRequest> getProducts(String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable);

    GetProductRequest getProduct(Long id);

    GetProductRequest createProduct(PostProductResponse postProductResponse);

    GetProductRequest updateProduct(Long id, PutProductResponse putProductResponse);

    void deleteProduct(Long id);
}
