package com.randomTechie.product_service.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductResponseTest {

	@Test
	void testProductResponseBuilder() {
		ProductResponse productResponse = ProductResponse.builder()
				.id("1")
				.name("Test Product")
				.description("Test Description")
				.price(BigDecimal.valueOf(100.0))
				.build();

		assertNotNull(productResponse);
		assertEquals("1", productResponse.getId());
		assertEquals("Test Product", productResponse.getName());
		assertEquals("Test Description", productResponse.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productResponse.getPrice());
	}

	@Test
	void testProductResponseSettersAndGetters() {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setId("1");
		productResponse.setName("Test Product");
		productResponse.setDescription("Test Description");
		productResponse.setPrice(BigDecimal.valueOf(100.0));

		assertEquals("1", productResponse.getId());
		assertEquals("Test Product", productResponse.getName());
		assertEquals("Test Description", productResponse.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productResponse.getPrice());
	}

	@Test
	void testProductResponseAllArgsConstructor() {
		ProductResponse productResponse = new ProductResponse("1", "Test Product", "Test Description", BigDecimal.valueOf(100.0));

		assertEquals("1", productResponse.getId());
		assertEquals("Test Product", productResponse.getName());
		assertEquals("Test Description", productResponse.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productResponse.getPrice());
	}

	@Test
	void testProductResponseNoArgsConstructor() {
		ProductResponse productResponse = new ProductResponse();

		assertNotNull(productResponse);
	}
}
