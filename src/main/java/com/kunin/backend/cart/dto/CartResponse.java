package com.kunin.backend.cart.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CartResponse {

    private final List<CartItemResponse> items;
    private final int totalPrice;
    private final int itemCount;
    private final boolean freeShipping;         // 5만원 이상 무료배송
    private final int shippingFee;

    private static final int FREE_SHIPPING_THRESHOLD = 50000;
    private static final int SHIPPING_FEE = 3000;

    public CartResponse(List<CartItemResponse> items) {
        this.items = items;
        this.totalPrice = items.stream().mapToInt(CartItemResponse::getTotalPrice).sum();
        this.itemCount = items.size();
        this.freeShipping = totalPrice >= FREE_SHIPPING_THRESHOLD;
        this.shippingFee = freeShipping ? 0 : SHIPPING_FEE;
    }
}
