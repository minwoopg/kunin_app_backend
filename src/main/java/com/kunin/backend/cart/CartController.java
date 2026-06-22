package com.kunin.backend.cart;

import com.kunin.backend.cart.dto.AddCartItemRequest;
import com.kunin.backend.cart.dto.CartItemResponse;
import com.kunin.backend.cart.dto.CartResponse;
import com.kunin.backend.cart.dto.UpdateCartItemRequest;
import com.kunin.backend.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart", description = "장바구니 API")
@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CartController {

    private final CartService cartService;

    /** GET /api/cart */
    @Operation(summary = "내 장바구니 조회")
    @GetMapping
    public ApiResponse<CartResponse> getCart(@AuthenticationPrincipal String email) {
        return ApiResponse.success(cartService.getCart(email));
    }

    /** POST /api/cart/items */
    @Operation(summary = "장바구니 상품 추가")
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CartItemResponse> addItem(
            @AuthenticationPrincipal String email,
            @Valid @RequestBody AddCartItemRequest request) {
        return ApiResponse.success(cartService.addItem(email, request), "장바구니에 추가되었습니다.");
    }

    /** PATCH /api/cart/items/{cartItemId} */
    @Operation(summary = "장바구니 수량 변경")
    @PatchMapping("/items/{cartItemId}")
    public ApiResponse<CartItemResponse> updateItem(
            @AuthenticationPrincipal String email,
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemRequest request) {
        return ApiResponse.success(cartService.updateItem(email, cartItemId, request));
    }

    /** DELETE /api/cart/items/{cartItemId} */
    @Operation(summary = "장바구니 항목 삭제")
    @DeleteMapping("/items/{cartItemId}")
    public ApiResponse<Void> deleteItem(
            @AuthenticationPrincipal String email,
            @PathVariable Long cartItemId) {
        cartService.deleteItem(email, cartItemId);
        return ApiResponse.success(null, "삭제되었습니다.");
    }

    /** DELETE /api/cart */
    @Operation(summary = "장바구니 전체 비우기")
    @DeleteMapping
    public ApiResponse<Void> clearCart(@AuthenticationPrincipal String email) {
        cartService.clearCart(email);
        return ApiResponse.success(null, "장바구니를 비웠습니다.");
    }
}
