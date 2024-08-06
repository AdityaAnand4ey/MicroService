package com.randomTechie.product_service.controller;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.dto.ProductResponse;
import com.randomTechie.product_service.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class ProductControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@InjectMocks
	private ProductController productController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void testCreateProduct() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		// Set fields for productRequest

		mockMvc.perform(post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"field1\":\"value1\", \"field2\":\"value2\"}")) // Replace with actual JSON
				.andExpect(status().isCreated());

		verify(productService, times(1)).createProduct(any(ProductRequest.class));
	}

	@Test
	void testGetAllProducts() throws Exception {
		ProductResponse productResponse1 = new ProductResponse();
		ProductResponse productResponse2 = new ProductResponse();
		// Set fields for productResponse1 and productResponse2

		List<ProductResponse> productResponses = Arrays.asList(productResponse1, productResponse2);
		when(productService.getAllProducts()).thenReturn(productResponses);

		mockMvc.perform(get("/api/product")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].field1").value("value1")) // Replace with actual field and value
				.andExpect(jsonPath("$[1].field1").value("value2")); // Replace with actual field and value

		verify(productService, times(1)).getAllProducts();
	}
}