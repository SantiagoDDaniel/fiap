package com.fiap.ecommerce.entities;

import com.fiap.ecommerce.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "TB_PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private double price;

    private String category;

    private Integer stockQuantity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(ProductDto productDto){
        this.name = productDto.name();
        this.description = productDto.description();
        this.price = productDto.price();
        this.category = productDto.category();
        this.stockQuantity = productDto.stockQuantity();
        this.imageUrl = productDto.imageUrl();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
