package com.kunin.backend.product;

import com.kunin.backend.common.CustomException;
import com.kunin.backend.common.ErrorCode;
import com.kunin.backend.product.dto.ProductDetailResponse;
import com.kunin.backend.product.dto.ProductSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 상품 목록 조회
     * categoryCode / keyword 파라미터에 따라 동적으로 필터링합니다.
     */
    public List<ProductSummaryResponse> getProducts(String categoryCode, String keyword) {
        boolean hasCategory = StringUtils.hasText(categoryCode);
        boolean hasKeyword = StringUtils.hasText(keyword);

        List<Product> products;

        if (hasCategory && hasKeyword) {
            products = productRepository.findByCategory_CodeAndNameContainingAndIsActiveTrue(categoryCode, keyword);
        } else if (hasCategory) {
            products = productRepository.findByCategory_CodeAndIsActiveTrue(categoryCode);
        } else if (hasKeyword) {
            products = productRepository.findByNameContainingAndIsActiveTrue(keyword);
        } else {
            products = productRepository.findByIsActiveTrue();
        }

        return products.stream()
                .map(ProductSummaryResponse::new)
                .toList();
    }

    /**
     * 상품 상세 조회
     */
    public ProductDetailResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(
                        ErrorCode.PRODUCT_NOT_FOUND,
                        "상품을 찾을 수 없습니다. (id: " + id + ")"
                ));

        return new ProductDetailResponse(product);
    }
}
