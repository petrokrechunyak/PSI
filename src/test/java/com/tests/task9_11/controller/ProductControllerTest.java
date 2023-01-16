package com.tests.task9_11.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tests.task9_11.Main;
import com.tests.task9_11.dto.SaveProductDto;
import com.tests.task9_11.exceptions.NotFoundException;
import com.tests.task9_11.model.Category;
import com.tests.task9_11.model.Product;
import com.tests.task9_11.services.CategoryService;
import com.tests.task9_11.services.ProductService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Main.class)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private Long testId;

    @BeforeEach
    public void prepare() {
        Category category = categoryService.findById(1L);
        Category category1 = categoryService.findById(2L);
        Category category2 = categoryService.findById(3L);

        productService.deleteAll();
        testId = productService.save(new SaveProductDto("product1", 200D,
                "Some biiiig decription", category));
        productService.save(new SaveProductDto("product1", 350.2,
                "Second biiiig decription", category1));
        productService.save(new SaveProductDto("product3", 24.55,
                "Third description", category));
        productService.save(new SaveProductDto("product4", 300D,
                "4 description", category));
        productService.save(new SaveProductDto("product5", 111.1,
                "5 description", category2));
    }

    @Test
    void getByIdTest() throws Exception{
        MvcResult mvcResult = mvc.perform(get("/product/" + testId)).andReturn();
        Product p = parseResponse(mvcResult, Product.class);
        assertThat(p.getId()).isEqualTo(testId);
    }

    @Test
    void findAllTest() throws Exception {
        MvcResult mvcResult = mvc.perform(get("/product")).andReturn();
        List<Product> products = parseResponse(mvcResult, List.class);
        Assertions.assertThat(products.size()).isEqualTo(5);
    }

    @Test
    void saveTest() throws Exception {
        String name = "Product new";
        Double price = 20D;
        String desc = "so long description";
        Long categoryId = 1L;
        String body = """
          {
              "name": "%s",
              "price": "%.2f",
              "description": "%s",
              "category": {
                "id":%d
              }
          }               
        """.formatted(name, price, desc, categoryId);
        MvcResult mvcResult = mvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isCreated())
                .andReturn();

        Long productId = parseResponse(mvcResult, Long.class);
        assertThat(productId).isGreaterThanOrEqualTo(1);

        Product product = productService.findById(productId);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getDescription()).isEqualTo(desc);
        assertThat(product.getCategory().getId()).isEqualTo(categoryId);
    }

    @Test
    void updateTest() throws Exception{
        String name = "Product new";
        Double price = 20D;
        String desc = "so long description";
        Long categoryId = 1L;
        String body = """
          {
              "name": "%s",
              "price": "%.2f",
              "description": "%s",
              "category": {
                "id":%d
              }
          }               
        """.formatted(name, price, desc, categoryId);
        mvc.perform(put("/product/" + testId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Product product = productService.findById(testId);
        assertThat(product).isNotNull();
        assertThat(product.getName()).isEqualTo(name);
        assertThat(product.getPrice()).isEqualTo(price);
        assertThat(product.getDescription()).isEqualTo(desc);
        assertThat(product.getCategory().getId()).isEqualTo(categoryId);
    }

    @Test
    void deleteTest() throws Exception {
        assertThat(productService.findById(testId)).isNotEqualTo(null);
        mvc.perform(delete("/product/" + testId))
                .andExpect(status().isNoContent());

        assertThatThrownBy(() -> productService.findById(testId))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void search() throws Exception {
        Double price = 300D;
        Long categoryId = 1L;
        int toReturn = 2;
        String body = """
          {
              "categoryId": %d,
              "page": 1,
              "size": %d
          }              
        """.formatted(categoryId, toReturn);
        MvcResult mvcResult = mvc.perform(post("/product/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                )
                .andReturn();

        List<Product> products = parseResponse(mvcResult, List.class);
        assertThat(products.size()).isEqualTo(toReturn);
    }

    private <T>T parseResponse(MvcResult mvcResult, Class<T> c) {
        try {
            return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), c);
        } catch (JsonProcessingException | UnsupportedEncodingException e) {
            throw new RuntimeException("Error parsing json", e);
        }
    }

}