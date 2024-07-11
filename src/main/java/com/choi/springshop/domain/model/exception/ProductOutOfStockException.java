package com.choi.springshop.domain.model.exception;

public class ProductOutOfStockException extends RuntimeException {
    public ProductOutOfStockException(String productName) {
        super("Product is out of stock: " + productName);
    }
}
