package com.choi.springshop.domain.repository.Product;

import com.choi.springshop.domain.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDslRepository {

    Page<Product> findProducts (String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable);
}
