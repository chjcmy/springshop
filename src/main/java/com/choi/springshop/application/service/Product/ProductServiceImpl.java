package com.choi.springshop.application.service.Product;

import com.choi.springshop.application.dto.Product.GetProductListRequest;
import com.choi.springshop.application.dto.Product.GetProductRequest;
import com.choi.springshop.application.dto.Product.PostProductResponse;
import com.choi.springshop.application.dto.Product.PutProductResponse;
import com.choi.springshop.domain.model.entity.Category;
import com.choi.springshop.domain.model.entity.Product;
import com.choi.springshop.domain.repository.Category.CategoryRepository;
import com.choi.springshop.domain.repository.Product.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<GetProductListRequest> getProducts(String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable) {
        return productRepository.findProducts(name, minPrice, maxPrice, categoryId, pageable)
                .map(this::convertToProductList);
    }

    @Override
    public GetProductRequest getProduct(Long id) {
        return productRepository.findById(id)
                .map(this::convertToProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @Transactional
    @Override
    public GetProductRequest createProduct(PostProductResponse postProductResponse) {
        Product product = convertToEntity(postProductResponse);
        return convertToProduct(productRepository.save(product));
    }

    @Transactional
    @Override
    public GetProductRequest updateProduct(Long id, PutProductResponse putProductResponse) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        updateProductFromDTO(product, putProductResponse);
        return convertToProduct(productRepository.save(product));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        productRepository.delete(product);

    }

    private GetProductListRequest convertToProductList(Product product) {
        GetProductListRequest request = new GetProductListRequest();
        request.setId(product.getId());
        request.setName(product.getName());
        request.setImageUrl(product.getImageUrl());
        request.setPrice(product.getPrice());
        return request;
    }

    private GetProductRequest convertToProduct(Product product) {
        Category category = new Category();

        category.setId(product.getCategory().getId());
        category.setName(product.getCategory().getName());

        return GetProductRequest.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .stockQuantity(product.getStockQuantity())
                .category(category)
                .build();
    }

    private Product convertToEntity(PostProductResponse postProductResponse) {
        Product product = new Product();
        product.setName(postProductResponse.getName());
        product.setDescription(postProductResponse.getDescription());
        product.setPrice(postProductResponse.getPrice());
        product.setImageUrl(postProductResponse.getImageUrl());
        product.setStockQuantity(postProductResponse.getStockQuantity());
        if (postProductResponse.getCategory() != null) {
            product.setCategory(categoryRepository.findById(postProductResponse.getCategory().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category")));
        }
        return product;
    }

    private void updateProductFromDTO(Product product, PutProductResponse putProductResponse) {
        product.setName(putProductResponse.getName());
        product.setDescription(putProductResponse.getDescription());
        product.setPrice(putProductResponse.getPrice());
        product.setImageUrl(putProductResponse.getImageUrl());
        product.setStockQuantity(putProductResponse.getStockQuantity());
        if (putProductResponse.getCategory() != null) {
            product.setCategory(categoryRepository.findById(putProductResponse.getCategory().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid category")));
        }
    }
}
