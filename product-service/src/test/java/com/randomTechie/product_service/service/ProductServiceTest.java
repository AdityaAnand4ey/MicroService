package com.randomTechie.product_service.service;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.dto.ProductResponse;
import com.randomTechie.product_service.model.Product;
import com.randomTechie.product_service.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@Mock
	private Logger log;

	@InjectMocks
	private ProductService productService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateProduct() {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setName("Test Product");
		productRequest.setDescription("Test Description");
		productRequest.setPrice(BigDecimal.valueOf(100.0));

		productService.createProduct(productRequest);

		ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
		verify(productRepository, times(1)).save(productCaptor.capture());
		verify(log, times(1)).info("Product {} is saved", productCaptor.getValue().getId());

		Product savedProduct = productCaptor.getValue();
		assertEquals("Test Product", savedProduct.getName());
		assertEquals("Test Description", savedProduct.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), savedProduct.getPrice());
	}

	@Test
	void testGetAllProducts() {
		Product product1 = new Product();
		product1.setId(String.valueOf(1L));
		product1.setName("Product 1");
		product1.setDescription("Description 1");
		product1.setPrice(BigDecimal.valueOf(100.0));

		Product product2 = new Product();
		product2.setId(String.valueOf(2L));
		product2.setName("Product 2");
		product2.setDescription("Description 2");
		product2.setPrice(BigDecimal.valueOf(200.0));

		List<Product> products = Arrays.asList(product1, product2);
		when(productRepository.findAll()).thenReturn(products);

		List<ProductResponse> productResponses = productService.getAllProducts();

		assertEquals(2, productResponses.size());
		assertEquals("Product 1", productResponses.get(0).getName());
		assertEquals("Description 1", productResponses.get(0).getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productResponses.get(0).getPrice());
		assertEquals("Product 2", productResponses.get(1).getName());
		assertEquals("Description 2", productResponses.get(1).getDescription());
		assertEquals(BigDecimal.valueOf(200.0), productResponses.get(1).getPrice());

		verify(productRepository, times(1)).findAll();
	}

	@Test
	void testMapToProductResponse() throws Exception {
		Product product = new Product();
		product.setId(String.valueOf(1L));
		product.setName("Test Product");
		product.setDescription("Test Description");
		product.setPrice(BigDecimal.valueOf(100.0));

		java.lang.reflect.Method method = ProductService.class.getDeclaredMethod("mapToProductResponse", Product.class);
		method.setAccessible(true);
		ProductResponse productResponse = (ProductResponse) method.invoke(productService, product);

		assertEquals(1L, productResponse.getId());
		assertEquals("Test Product", productResponse.getName());
		assertEquals("Test Description", productResponse.getDescription());
		assertEquals(BigDecimal.valueOf(100.0), productResponse.getPrice());
	}
}
