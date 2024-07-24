package com.choi.springshop.application.service.Cart;

import com.choi.springshop.application.dto.Cart.GetItemResponse;
import com.choi.springshop.application.dto.Cart.PutItemRequest;
import com.choi.springshop.domain.model.entity.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface CartService {
    Cart createCart(String userId);

    ResponseEntity<List<GetItemResponse>> getCartByUserId(String name);

    ResponseEntity<GetItemResponse> updateCartItems(Long cartItemId, PutItemRequest putItemResquests);

    ResponseEntity<String> removeCartItem(Long cartId, Long itemId);
}
