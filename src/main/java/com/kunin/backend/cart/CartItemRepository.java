package com.kunin.backend.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /** 내 장바구니 전체 조회 (product, category 한번에 fetch) */
    @Query("SELECT ci FROM CartItem ci " +
           "JOIN FETCH ci.product p " +
           "JOIN FETCH p.category " +
           "WHERE ci.user.id = :userId " +
           "ORDER BY ci.createdAt DESC")
    List<CartItem> findByUserIdWithProduct(@Param("userId") Long userId);

    /** 특정 상품이 이미 장바구니에 있는지 확인 */
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

    /** 내 장바구니 전체 삭제 */
    void deleteByUserId(Long userId);

    /** 해당 cartItem이 내 것인지 확인 */
    Optional<CartItem> findByIdAndUserId(Long id, Long userId);
}
