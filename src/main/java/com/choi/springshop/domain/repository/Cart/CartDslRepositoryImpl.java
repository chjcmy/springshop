package com.choi.springshop.domain.repository.Cart;

import com.choi.springshop.application.dto.Cart.GetItemResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.choi.springshop.domain.model.entity.QCartItem.cartItem;
import static com.choi.springshop.domain.model.entity.QProduct.product;

@Repository
public class CartDslRepositoryImpl implements CartDslRepository {

    private final JPAQueryFactory queryFactory;

    public CartDslRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<GetItemResponse> findCartItemsWithProducts(Long cartId) {
        return queryFactory
                .select(Projections.constructor(GetItemResponse.class,
                        product.id,
                        product.name,
                        product.price,
                        cartItem.quantity
                ))
                .from(cartItem)
                .leftJoin(cartItem.product, product)
                .where(cartItem.cart.id.eq(cartId))
                .where(cartItem.active.eq(true))
                .fetch();
    }






}
