package com.fiap.ecommerce.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ItemDto(
        @NotBlank(message = "O nome do produto é obrigatório")
        String name,
        @Min(value = 1, message = "A quantidade do produto deve ser maior que zero")
        Integer amount,
        @DecimalMin(value = "0.01", message = "O preço do produto deve ser maior que zero")
        BigDecimal price

) {
}
