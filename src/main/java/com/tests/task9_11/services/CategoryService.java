package com.tests.task9_11.services;

import com.tests.task9_11.model.Category;

import java.util.List;

public interface CategoryService {

    Long save(Category category);

    Category findById(Long id);

    List<Category> findAll();

    void deleteAll();
}
