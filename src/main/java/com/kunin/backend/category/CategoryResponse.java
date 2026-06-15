package com.kunin.backend.category;

import lombok.Getter;

@Getter
public class CategoryResponse {

    private final Long id;
    private final String code;
    private final String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.code = category.getCode();
        this.name = category.getName();
    }
}
