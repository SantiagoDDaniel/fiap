package com.fiap.ecommerce.services;

import com.fiap.ecommerce.dtos.ItemDto;
import com.fiap.ecommerce.dtos.ItemResponseDto;

public interface ItemService {
    ItemResponseDto save(ItemDto request);
    ItemResponseDto update(ItemDto request, Long id);
}
