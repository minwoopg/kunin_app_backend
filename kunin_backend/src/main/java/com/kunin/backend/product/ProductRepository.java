package com.kunin.backend.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIsActiveTrue();

    List<Product> findByCategory_CodeAndIsActiveTrue(String categoryCode);

    List<Product> findByNameContainingAndIsActiveTrue(String keyword);

    List<Product> findByCategory_CodeAndNameContainingAndIsActiveTrue(String categoryCode, String keyword);
}
