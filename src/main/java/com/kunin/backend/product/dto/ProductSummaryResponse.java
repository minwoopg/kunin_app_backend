package com.kunin.backend.product.dto;

import com.kunin.backend.product.Product;
import lombok.Getter;

/**
 * 상품 목록(GET /api/products) 응답
 */
@Getter
public class ProductSummaryResponse {

    private final Long id;
    private final String name;
    private final String categoryCode;
    private final String categoryName;
    private final Integer price;
    private final String description;
    private final String imageUrl;
    private final String tag;
    private final Double rating;
    private final Integer reviewCount;
    private final Integer stock;
    private final boolean isSoldOut;

    public ProductSummaryResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.categoryCode = product.getCategory().getCode();
        this.categoryName = product.getCategory().getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.imageUrl = product.getImageUrl();
        this.tag = product.getTag().name();
        this.rating = product.getRating();
        this.reviewCount = product.getReviewCount();
        this.stock = product.getStockQuantity();
        this.isSoldOut = product.isSoldOut();
    }
}
