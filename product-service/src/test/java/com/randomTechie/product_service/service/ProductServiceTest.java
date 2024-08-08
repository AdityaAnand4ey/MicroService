package com.randomTechie.product_service.service;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.dto.ProductResponse;
import com.randomTechie.product_service.model.Product;
import com.randomTechie.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	void shouldCreateProduct() {
		ProductRequest productRequest = new ProductRequest("Product1", "Description1", BigDecimal.valueOf(100));
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.build();

		productService.createProduct(productRequest);

		verify(productRepository, times(1)).save(product);
	}

	@Test
	void shouldReturnAllProducts() {
		Product product = Product.builder()
				.id("1")
				.name("Product1")
				.description("Description1")
				.price(BigDecimal.valueOf(100))
				.build();

		when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

		List<ProductResponse> products = productService.getAllProducts();

		assertEquals(1, products.size());
		assertEquals("Product1", products.get(0).getName());
	}
}
