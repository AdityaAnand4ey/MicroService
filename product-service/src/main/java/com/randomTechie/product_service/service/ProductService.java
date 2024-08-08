package com.randomTechie.product_service.service;

import com.randomTechie.product_service.dto.ProductRequest;
import com.randomTechie.product_service.dto.ProductResponse;
import com.randomTechie.product_service.mapper.ProductMapper;
import com.randomTechie.product_service.model.Product;
import com.randomTechie.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    public void createProduct(ProductRequest productRequest){
        Product product = productMapper.toProduct(productRequest);
        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(productMapper::toProductResponse).toList();
    }
}
