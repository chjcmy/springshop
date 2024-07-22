package com.choi.springshop.domain.repository.Cart;

import com.choi.springshop.application.dto.Cart.GetItemResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDslRepository {

    List<GetItemResponse> findCartItemsWithProducts(Long cartId);
}
