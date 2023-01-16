package com.tests.task9_11;

import com.tests.task9_11.model.Category;
import com.tests.task9_11.model.Product;
import com.tests.task9_11.repo.CategoryRepo;
import com.tests.task9_11.repo.ProductRepo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    InitializingBean initialize() {
        return () -> {
            Category category = categoryRepo.save(new Category("category1"));
            Category category1 = categoryRepo.save(new Category("category2"));
            Category category2 = categoryRepo.save(new Category("category3"));
            productRepo.save(new Product("product1", 200D,
                    "Some biiiig decription", category));
            productRepo.save(new Product("product1", 350.2,
                    "Second biiiig decription", category1));
            productRepo.save(new Product("product3", 24.55,
                    "Third description", category));
            productRepo.save(new Product("product4", 300D,
                    "4 description", category));
            productRepo.save(new Product("product5", 111.1,
                    "5 description", category2));
        };
    }
}
