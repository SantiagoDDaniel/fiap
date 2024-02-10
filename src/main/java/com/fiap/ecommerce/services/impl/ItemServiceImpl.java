package com.fiap.ecommerce.services.impl;

import com.fiap.ecommerce.dtos.ItemDto;
import com.fiap.ecommerce.dtos.ItemResponseDto;
import com.fiap.ecommerce.models.Item;
import com.fiap.ecommerce.repositories.ItemRepository;
import com.fiap.ecommerce.services.ItemService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository repository;

    @Override
    public ItemResponseDto save(ItemDto request) {
        Item model = new Item(request.name(), request.amount(), request.price());
        Item response = repository.save(model);
        return new ItemResponseDto(response.getId(), response.getName(), response.getAmount(), response.getPrice());
    }

    @Override
    public ItemResponseDto update(ItemDto request, Long id) {
        Optional<Item> itemOptional = repository.findById(id);
        if (itemOptional.isPresent()) {
            Item existingItem = itemOptional.get();
            existingItem.setName(request.name());
            existingItem.setAmount(request.amount());
            existingItem.setPrice(request.price());
            Item updatedItem = repository.save(existingItem);
            return new ItemResponseDto(updatedItem.getId(), updatedItem.getName(), updatedItem.getAmount(), updatedItem.getPrice());
        } else {
            throw new EntityNotFoundException("Item com ID " + id + " não encontrado.");
        }
    }
}
