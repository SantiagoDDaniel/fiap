package com.fiap.ecommerce.services;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.ecommerce.dto.ProductDto;
import com.fiap.ecommerce.entities.Product;
import com.fiap.ecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(ProductDto productDto) {
        Product product = new Product(productDto);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductDto product) {
        Product existingProduct = getProductById(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(product.name());
        existingProduct.setDescription(product.description());
        existingProduct.setPrice(product.price());
        existingProduct.setCategory(product.category());
        existingProduct.setUpdatedAt(LocalDateTime.now());
        existingProduct.setImageUrl(product.imageUrl());
        existingProduct.setStockQuantity(product.stockQuantity());
        return productRepository.save(existingProduct);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @KafkaListener(topics = "product-details-request", groupId = "group-3")
    public void receiveProductDetailsRequest(Long productId) throws JsonProcessingException {
        Product product = this.getProductById(productId);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(product);

        kafkaTemplate.send("product-details-response", json);
    }
}
