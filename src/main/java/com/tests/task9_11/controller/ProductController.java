package com.tests.task9_11.controller;

import com.tests.task9_11.dto.SaveProductDto;
import com.tests.task9_11.dto.SearchProductDto;
import com.tests.task9_11.model.Product;
import com.tests.task9_11.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Long> save(@Valid @RequestBody SaveProductDto productDto) {
        Long id = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody SaveProductDto productDto) {
        productService.update(id, productDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public List<Product> search(@Valid @RequestBody SearchProductDto searchProductDto) {
        return productService.findByPriceAndCategoryIdWithPagination(searchProductDto).stream().toList();
    }
}
