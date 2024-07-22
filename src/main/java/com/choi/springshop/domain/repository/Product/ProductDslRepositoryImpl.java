package com.choi.springshop.domain.repository.Product;

import com.choi.springshop.domain.model.entity.Product;
import com.choi.springshop.domain.model.entity.QProduct;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class ProductDslRepositoryImpl implements ProductDslRepository {

    private final JPAQueryFactory queryFactory;

    public ProductDslRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Product> findProducts(String name, Float minPrice, Float maxPrice, Long categoryId, Pageable pageable) {
        QProduct product = QProduct.product;

        BooleanExpression predicate = product.isNotNull();
        predicate = name != null ? predicate.and(eqName(name)) : predicate;
        predicate = minPrice != null ? predicate.and(eqMinPrice(minPrice)) : predicate;
        predicate = maxPrice != null ? predicate.and(eqMaxPrice(maxPrice)) : predicate;
        predicate = categoryId != null ? predicate.and(eqCategoryId(categoryId)) : predicate;
        predicate = predicate.and(product.stockQuantity.goe(0));

        List<Product> content = queryFactory
                .selectFrom(product)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(product.count())
                .from(product)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pageable, total != null ? total : 0L);
    }

    private BooleanExpression eqName(String name) {
        return StringUtils.hasText(name) ? QProduct.product.name.contains(name) : null;
    }

    private BooleanExpression eqMinPrice(Float minPrice) {
        return minPrice != null ? QProduct.product.price.goe(minPrice) : null;
    }

    private BooleanExpression eqMaxPrice(Float maxPrice) {
        return maxPrice != null ? QProduct.product.price.loe(maxPrice) : null;
    }

    private BooleanExpression eqCategoryId(Long categoryId) {
        return categoryId != null ? QProduct.product.category.id.eq(categoryId) : null;
    }
}