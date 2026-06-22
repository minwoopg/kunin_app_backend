package com.kunin.backend.cart.dto;

import com.kunin.backend.cart.CartItem;
import lombok.Getter;

@Getter
public class CartItemResponse {

    private final Long cartItemId;
    private final Long productId;
    private final String productName;
    private final String categoryCode;
    private final String categoryName;
    private final Integer price;
    private final String imageUrl;
    private final String tag;
    private final Integer quantity;
    private final Integer totalPrice;
    private final Integer stock;
    private final boolean isSoldOut;

    public CartItemResponse(CartItem cartItem) {
        this.cartItemId = cartItem.getId();
        this.productId = cartItem.getProduct().getId();
        this.productName = cartItem.getProduct().getName();
        this.categoryCode = cartItem.getProduct().getCategory().getCode();
        this.categoryName = cartItem.getProduct().getCategory().getName();
        this.price = cartItem.getProduct().getPrice();
        this.imageUrl = cartItem.getProduct().getImageUrl();
        this.tag = cartItem.getProduct().getTag().name();
        this.quantity = cartItem.getQuantity();
        this.totalPrice = cartItem.getProduct().getPrice() * cartItem.getQuantity();
        this.stock = cartItem.getProduct().getStockQuantity();
        this.isSoldOut = cartItem.getProduct().isSoldOut();
    }
}
