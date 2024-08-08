package com.randomTechie.product_service;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.model.Product;
import com.randomTechie.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldCreateProduct() {
        ProductRequest productRequest = new ProductRequest("Product1", "Description1", BigDecimal.valueOf(100));
        HttpEntity<ProductRequest> request = new HttpEntity<>(productRequest);

        ResponseEntity<Void> response = restTemplate.postForEntity("/api/product", request, Void.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        List<Product> products = productRepository.findAll();
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getName());
    }

    @Test
    void shouldReturnAllProducts() {
        Product product = Product.builder()
                .name("Product1")
                .description("Description1")
                .price(BigDecimal.valueOf(100))
                .build();
        productRepository.save(product);

        ResponseEntity<Product[]> response = restTemplate.getForEntity("/api/product", Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().length);
        assertEquals("Product1", response.getBody()[0].getName());
    }
}
