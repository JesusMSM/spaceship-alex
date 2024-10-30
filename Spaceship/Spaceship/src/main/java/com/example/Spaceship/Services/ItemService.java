package com.example.Spaceship.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Item;
import com.example.Spaceship.repositories.ItemRepository;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
    
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(Long id, Item newItemData) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(newItemData.getName());
                    existingItem.setWeight(newItemData.getWeight());
                    existingItem.setDescription(newItemData.getDescription());
                    existingItem.setCategory(newItemData.getCategory());
                    return itemRepository.save(existingItem);
                })
                .orElseThrow(() -> new IllegalArgumentException("Item with ID " + id + " not found"));
    }

    public void deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Item with ID " + id + " not found");
        }
    }
}
