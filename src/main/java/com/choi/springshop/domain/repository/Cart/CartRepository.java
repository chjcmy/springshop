package com.choi.springshop.domain.repository.Cart;

import com.choi.springshop.domain.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long>, CartDslRepository {
    List<Cart> findByUserIdOrderByCreatedAtDesc(Long userId);
}
