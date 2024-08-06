package com.randomTechie.product_service.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {

	@Test
	void testProductBuilder() {
		Product product = Product.builder()
				.id("1")
				.name("Test Product")
				.description("Test Description")
				.price(BigDecimal.valueOf(100.0))
				.build();

		assertNotNull(product);
		assertEquals("1", product.getId());
		assertEquals("Test Product", product.getName());
		assertEquals("Test Description", product.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), product.getPrice());
	}

	@Test
	void testProductSettersAndGetters() {
		Product product = new Product();
		product.setId("1");
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(BigDecimal.valueOf(100.0));

		assertEquals("1", product.getId());
		assertEquals("Test Product", product.getName());
		assertEquals("Test Description", product.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), product.getPrice());
	}

	@Test
	void testProductAllArgsConstructor() {
		Product product = new Product("1", "Test Product", "Test Description", BigDecimal.valueOf(100.0));

		assertEquals("1", product.getId());
		assertEquals("Test Product", product.getName());
		assertEquals("Test Description", product.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), product.getPrice());
	}

	@Test
	void testProductNoArgsConstructor() {
		Product product = new Product();

		assertNotNull(product);
	}
}
