package com.randomTechie.product_service.controller;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.dto.ProductResponse;
import com.randomTechie.product_service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = new ProductRequest("Product1", "Description1", BigDecimal.valueOf(100));

		mockMvc.perform(post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Product1\",\"description\":\"Description1\",\"price\":100}"))
				.andExpect(status().isCreated());
	}

	@Test
	void shouldReturnAllProducts() throws Exception {
		ProductResponse productResponse = new ProductResponse("1", "Product1", "Description1", BigDecimal.valueOf(100));
		Mockito.when(productService.getAllProducts()).thenReturn(Collections.singletonList(productResponse));

		mockMvc.perform(get("/api/product"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is("Product1")));
	}
}
