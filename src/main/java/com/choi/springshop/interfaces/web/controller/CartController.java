package com.choi.springshop.interfaces.web.controller;

import com.choi.springshop.application.dto.Cart.GetItemResponse;
import com.choi.springshop.application.dto.Cart.PutItemRequest;
import com.choi.springshop.application.service.Cart.CartService;
import com.choi.springshop.domain.model.entity.Cart;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart(
            Authentication authentication
    ) {
        Cart newCart = cartService.createCart(authentication.getName());
        return ResponseEntity.ok(newCart);
    }

    @GetMapping
    public ResponseEntity<List<GetItemResponse>> getCart(
            Authentication authentication
    ) {
        return cartService.getCartByUserId(authentication.getName());
    }

    @PutMapping("/{cartId}/items")
    public ResponseEntity<GetItemResponse> updateCartItems(@PathVariable Long cartId, @RequestBody PutItemRequest putItemRequests) {
        return cartService.updateCartItems(cartId, putItemRequests);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartId, @PathVariable Long itemId, Authentication authentication) {
        return cartService.removeCartItem(cartId, itemId);
    }
}