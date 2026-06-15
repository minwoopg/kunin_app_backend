package com.kunin.backend.product;

import com.kunin.backend.common.ApiResponse;
import com.kunin.backend.product.dto.ProductDetailResponse;
import com.kunin.backend.product.dto.ProductSummaryResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "상품 API")
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * GET /api/products
     * GET /api/products?categoryCode=medicalDevice
     * GET /api/products?keyword=초음파
     */
    @GetMapping
    public ApiResponse<List<ProductSummaryResponse>> getProducts(
            @RequestParam(required = false) String categoryCode,
            @RequestParam(required = false) String keyword
    ) {
        return ApiResponse.success(productService.getProducts(categoryCode, keyword));
    }

    /** GET /api/products/{id} */
    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> getProduct(@PathVariable Long id) {
        return ApiResponse.success(productService.getProduct(id));
    }
}
