package com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

public record ItemResponseDto(
        Long id,
        String name,
        Integer amount,
        BigDecimal price

) {
}
