package com.fiap.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public record ProductDto(
        @NotBlank(message = "Name obrigatorio") String name,
        @NotBlank(message = "Description obrigatorio") String description,
        @Positive(message = "Price deve ser positivo") double price,
        @NotBlank(message = "Category obrigatoirio") String category,
        @PositiveOrZero(message = "Stock Quantity deve ser maior ou igual a zero") Integer stockQuantity,
        @NotBlank(message = "Image URL obrigatoria") String imageUrl) {

    public ProductDto(String name, String description, double price, String category, Integer stockQuantity, String imageUrl){
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.imageUrl = imageUrl;
    }

    public String name() {
        return this.name;
    }

    public String description() {
        return this.description;
    }

    public double price() {
        return this.price;
    }

    public String category() {
        return this.category;
    }

    public Integer stockQuantity() {
        return this.stockQuantity;
    }

    public String imageUrl() {
        return this.imageUrl;
    }
    
}
