package com.kunin.backend.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByIsActiveTrueOrderByDisplayOrderAsc();

    Optional<Category> findByCodeAndIsActiveTrue(String code);
}
