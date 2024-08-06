package com.randomTechie.product_service.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductRequestTest {

	@Test
	void testProductRequestBuilder() {
		ProductRequest productRequest = ProductRequest.builder()
				.name("Test Product")
				.description("Test Description")
				.price(BigDecimal.valueOf(100.0))
				.build();

		assertNotNull(productRequest);
		assertEquals("Test Product", productRequest.getName());
		assertEquals("Test Description", productRequest.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productRequest.getPrice());
	}

	@Test
	void testProductRequestSettersAndGetters() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("Test Product");
		productRequest.setDescription("Test Description");
		productRequest.setPrice(BigDecimal.valueOf(100.0));

		assertEquals("Test Product", productRequest.getName());
		assertEquals("Test Description", productRequest.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productRequest.getPrice());
	}

	@Test
	void testProductRequestAllArgsConstructor() {
		ProductRequest productRequest = new ProductRequest("Test Product", "Test Description", BigDecimal.valueOf(100.0));

		assertEquals("Test Product", productRequest.getName());
		assertEquals("Test Description", productRequest.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productRequest.getPrice());
	}

	@Test
	void testProductRequestNoArgsConstructor() {
		ProductRequest productRequest = new ProductRequest();

		assertNotNull(productRequest);
	}
}
