package com.tests.task9_11.services;

import com.tests.task9_11.dto.SaveProductDto;
import com.tests.task9_11.dto.SearchProductDto;
import com.tests.task9_11.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Long save(SaveProductDto product);

    Product findById(Long id);

    void update(Long id, SaveProductDto product);

    void delete(Long id);

    List<Product> findByPriceAndCategoryIdWithPagination(SearchProductDto searchProductDto);

    void deleteAll();

}
