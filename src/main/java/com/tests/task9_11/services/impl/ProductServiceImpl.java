package com.tests.task9_11.services.impl;

import com.tests.task9_11.dto.SaveProductDto;
import com.tests.task9_11.dto.SearchProductDto;
import com.tests.task9_11.exceptions.NotFoundException;
import com.tests.task9_11.model.Product;
import com.tests.task9_11.repo.ProductRepo;
import com.tests.task9_11.services.CategoryService;
import com.tests.task9_11.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final CategoryService categoryService;


    @Override
    public List<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Long save(SaveProductDto product) {
        Product p = new Product();
        convertProductDtoToProduct(p, product);
        return productRepo.save(p).getId();
    }

    @Override
    public Product findById(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Product with id %d does not exist".formatted(id))
        );
    }

    @Override
    public void update(Long id, SaveProductDto productToUpdate) {
        Product product = findById(id);
        convertProductDtoToProduct(product, productToUpdate);
        productRepo.save(product);
    }

    @Override
    public void delete(Long id) {
        Product p = findById(id);
        productRepo.delete(p);
    }

    @Override
    public List<Product> findByPriceAndCategoryIdWithPagination(SearchProductDto searchProductDto) {
        List<Product> products = productRepo.findAll()
                .stream()
                .filter((x) -> isMatch(x, searchProductDto))
                .toList();
        int from = (searchProductDto.getPage() - 1) * searchProductDto.getSize();
        int to = from + searchProductDto.getSize();
        return products.subList(
                from > products.size() ? 0 : from,
                Math.min(to, products.size())
        );
    }

    @Override
    public void deleteAll() {
        productRepo.deleteAll();
    }

    private boolean isMatch(Product p, SearchProductDto search) {
        boolean result = true;
        if (search.getPrice() != null && search.getPrice() > 0) {
            result = p.getPrice().equals(search.getPrice());
        }
        if (result && search.getCategoryId() != null && search.getCategoryId() > 0) {
            result = p.getCategory().getId().equals(search.getCategoryId());
        }
        return result;
    }

    private void convertProductDtoToProduct(Product product, SaveProductDto saveProductDto) {
        product.setName(saveProductDto.getName());
        product.setPrice(saveProductDto.getPrice());
        product.setDescription(saveProductDto.getDescription());
        product.setCategory(saveProductDto.getCategory());
    }
}
