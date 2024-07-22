package com.choi.springshop.application.dto.Cart;

import com.choi.springshop.domain.model.entity.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class PutItemRequest {
    private Long productId;
    private Integer quantity;
}
