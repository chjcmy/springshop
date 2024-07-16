package com.choi.springshop.application.service.Product;

import com.choi.springshop.application.dto.Product.GetProductResponse;
import com.choi.springshop.domain.model.entity.Product;
import com.choi.springshop.domain.repository.Product.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<GetProductResponse> getProducts(String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable) {
        return productRepository.findProducts(name, minPrice, maxPrice, categoryId, pageable)
                .map(this::convertToDTO);
    }

    private GetProductResponse convertToDTO(Product product) {
        return new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
