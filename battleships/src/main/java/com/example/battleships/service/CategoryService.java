package com.example.battleships.service;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.enums.CategoryNameEnum;

import java.util.Optional;

public interface CategoryService {
    void initCategories();


    Optional<Category> findBuCategoryEnumName(CategoryNameEnum nameEnum);
}
