package com.example.battleships.service.impl;

import com.example.battleships.model.entity.Category;
import com.example.battleships.model.enums.CategoryNameEnum;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategories() {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category(CategoryNameEnum.CARGO));
            categoryRepository.save(new Category(CategoryNameEnum.BATTLE));
            categoryRepository.save(new Category(CategoryNameEnum.PATROL));
        }
    }

    @Override
    public Optional<Category> findBuCategoryEnumName(CategoryNameEnum nameEnum) {
        return categoryRepository.findCategoryByName(nameEnum);
    }

}
