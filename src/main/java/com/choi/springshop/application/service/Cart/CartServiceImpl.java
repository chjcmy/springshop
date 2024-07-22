package com.choi.springshop.application.service.Cart;

import com.choi.springshop.application.dto.Cart.GetItemResponse;
import com.choi.springshop.application.dto.Cart.PutItemRequest;
import com.choi.springshop.domain.model.entity.Cart;
import com.choi.springshop.domain.model.entity.CartItem;
import com.choi.springshop.domain.model.entity.Product;
import com.choi.springshop.domain.model.entity.User;
import com.choi.springshop.domain.model.exception.ActiveCartAlreadyExistsException;
import com.choi.springshop.domain.model.valueobject.Cart.CartStatus;
import com.choi.springshop.domain.repository.Cart.CartRepository;
import com.choi.springshop.domain.repository.Product.ProductRepository;
import com.choi.springshop.domain.repository.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Cart createCart(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userName));

        if (user.getActiveCart() != null) {
            throw new ActiveCartAlreadyExistsException("User already has an active cart: " + userName);
        }

        Cart newCart = createAndInitializeCart(user);

        return cartRepository.save(newCart);
    }

    @Override
    public ResponseEntity<List<GetItemResponse>> getCartByUserId(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + userName));

        if (user.getActiveCart() == null) {
            createAndInitializeCart(user);
        }

        List<GetItemResponse> cartItems = cartRepository.findCartItemsWithProducts(user.getActiveCart().getId());
        return ResponseEntity.ok(cartItems);
    }

    @Override
    @Transactional
    public ResponseEntity<String> removeCartItem(Long cartId, Long itemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found: " + cartId));

        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Cart item not found: " + itemId));

        itemToRemove.setActive(false);
        cart.setUpdatedAt(new Date());

        return ResponseEntity.ok().body("Cart item with ID " + itemId + " removed successfully.");
    }

    @Override
    @Transactional
    public ResponseEntity<GetItemResponse> updateCartItems(Long cartId, PutItemRequest putItemRequest) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found: " + cartId));

        Product product = findProductById(putItemRequest.getProductId());
        if (product == null) {
            throw new EntityNotFoundException("Product not found: " + putItemRequest.getProductId());
        }

        CartItem updatedCartItem = updateCartItem(cart, product, putItemRequest);

        cart.setUpdatedAt(new Date());
        cartRepository.save(cart);

        GetItemResponse response = GetItemResponse.builder()
                .id(updatedCartItem.getId())
                .productName(updatedCartItem.getProduct().getName())
                .price(updatedCartItem.getProduct().getPrice())
                .quantity(updatedCartItem.getQuantity())
                .build();

        return ResponseEntity.ok(response);
    }

    private Product findProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    private CartItem updateCartItem(Cart cart, Product product, PutItemRequest putItemRequest) {
        Optional<CartItem> existingItemOpt = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(putItemRequest.getProductId()))
                .findFirst();

        CartItem updatedCartItem;
        if (existingItemOpt.isPresent()) {
            updatedCartItem = existingItemOpt.get();
        } else {
            updatedCartItem = new CartItem();
            cart.addItem(updatedCartItem);
        }

        updatedCartItem.setProduct(product);
        updatedCartItem.setQuantity(putItemRequest.getQuantity());
        return updatedCartItem;
    }

    @Transactional
    public void completeCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        cart.setStatus(CartStatus.COMPLETED);
        cart.setUpdatedAt(new Date());
        User user = cart.getUser();
        user.setActiveCart(null);
        createCart(user.getUsername());  // Create a new active cart for the user
    }

    public List<Cart> getUserCartHistory(Long userId) {
        return cartRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    private Cart createAndInitializeCart(User user) {
        Cart newCart = new Cart();
        newCart.setUser(user);
        newCart.setStatus(CartStatus.ACTIVE);
        newCart.setCreatedAt(new Date());
        newCart.setUpdatedAt(new Date());

        user.addCart(newCart);
        user.setActiveCart(newCart);

        return newCart;
    }
}