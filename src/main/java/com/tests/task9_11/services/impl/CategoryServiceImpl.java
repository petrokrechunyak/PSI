package com.tests.task9_11.services.impl;

import com.tests.task9_11.exceptions.NotFoundException;
import com.tests.task9_11.model.Category;
import com.tests.task9_11.repo.CategoryRepo;
import com.tests.task9_11.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Long save(Category category) {
        return categoryRepo.save(category).getId();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Category with id %d does not exist".formatted(id))
        );
    }

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public void deleteAll() {
        categoryRepo.deleteAll();
    }
}
