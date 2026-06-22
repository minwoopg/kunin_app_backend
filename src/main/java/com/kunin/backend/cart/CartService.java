package com.kunin.backend.cart;

import com.kunin.backend.cart.dto.AddCartItemRequest;
import com.kunin.backend.cart.dto.CartItemResponse;
import com.kunin.backend.cart.dto.CartResponse;
import com.kunin.backend.cart.dto.UpdateCartItemRequest;
import com.kunin.backend.common.CustomException;
import com.kunin.backend.common.ErrorCode;
import com.kunin.backend.product.Product;
import com.kunin.backend.product.ProductRepository;
import com.kunin.backend.user.User;
import com.kunin.backend.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    /** 내 장바구니 조회 */
    public CartResponse getCart(String email) {
        User user = getUser(email);
        List<CartItemResponse> items = cartItemRepository
                .findByUserIdWithProduct(user.getId())
                .stream()
                .map(CartItemResponse::new)
                .toList();
        return new CartResponse(items);
    }

    /** 장바구니 상품 추가 */
    @Transactional
    public CartItemResponse addItem(String email, AddCartItemRequest request) {
        User user = getUser(email);
        Product product = getProduct(request.getProductId());

        // 품절 체크
        if (product.isSoldOut()) {
            throw new CustomException(ErrorCode.PRODUCT_SOLD_OUT);
        }

        // 이미 담긴 상품이면 수량 합산
        return cartItemRepository.findByUserIdAndProductId(user.getId(), product.getId())
                .map(existing -> {
                    int newQty = existing.getQuantity() + request.getQuantity();
                    // 재고 초과 체크
                    if (newQty > product.getStockQuantity()) {
                        throw new CustomException(ErrorCode.CART_QUANTITY_EXCEEDED);
                    }
                    existing.addQuantity(request.getQuantity());
                    return new CartItemResponse(existing);
                })
                .orElseGet(() -> {
                    // 재고 초과 체크
                    if (request.getQuantity() > product.getStockQuantity()) {
                        throw new CustomException(ErrorCode.CART_QUANTITY_EXCEEDED);
                    }
                    CartItem cartItem = CartItem.builder()
                            .user(user)
                            .product(product)
                            .quantity(request.getQuantity())
                            .build();
                    return new CartItemResponse(cartItemRepository.save(cartItem));
                });
    }

    /** 수량 변경 */
    @Transactional
    public CartItemResponse updateItem(String email, Long cartItemId, UpdateCartItemRequest request) {
        User user = getUser(email);
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.CART_ITEM_NOT_FOUND));

        // 재고 초과 체크
        if (request.getQuantity() > cartItem.getProduct().getStockQuantity()) {
            throw new CustomException(ErrorCode.CART_QUANTITY_EXCEEDED);
        }

        cartItem.updateQuantity(request.getQuantity());
        return new CartItemResponse(cartItem);
    }

    /** 항목 삭제 */
    @Transactional
    public void deleteItem(String email, Long cartItemId) {
        User user = getUser(email);
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, user.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.CART_ITEM_NOT_FOUND));
        cartItemRepository.delete(cartItem);
    }

    /** 전체 비우기 */
    @Transactional
    public void clearCart(String email) {
        User user = getUser(email);
        cartItemRepository.deleteByUserId(user.getId());
    }

    // ── 내부 헬퍼 ──────────────────────────────────────────────────────────

    private User getUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    private Product getProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
    }
}
