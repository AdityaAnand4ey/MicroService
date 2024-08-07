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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService productService;

	@Test
	public void testCreateProduct() throws Exception {
		ProductRequest productRequest = new ProductRequest();

		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"field1\":\"value1\", \"field2\":\"value2\"}"))
				.andExpect(status().isCreated());
	}

	@Test
	public void testGetAllProducts() throws Exception {
		ProductResponse productResponse1 = new ProductResponse();
		ProductResponse productResponse2 = new ProductResponse();

		List<ProductResponse> productResponses = Arrays.asList(productResponse1, productResponse2);

		Mockito.when(productService.getAllProducts()).thenReturn(productResponses);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].field1", is("value1")))
				.andExpect(jsonPath("$[1].field2", is("value2")));
	}
}
