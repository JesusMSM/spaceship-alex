package com.example.Spaceship.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Item;
import com.example.Spaceship.repositories.ItemRepository;

@Service
public class CalculatorService {

    private final ItemRepository itemRepository;

    private static final double WEIGHT_LIMIT_TONS = 150.0; //Weight limit in tonnes
    private static final double WEIGHT_LIMIT_KG = WEIGHT_LIMIT_TONS * 1000.0; //Conversion to kg and weight limit in kg

    //Constructor for injecting dependencies
    public CalculatorService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Method to check if the total weight of the selected items is allowed
    public boolean isWeightWithinLimit(Map<Long, Integer> itemQuantities){
        double totalWeight = calculateTotalWeight(itemQuantities);
        return totalWeight <= WEIGHT_LIMIT_KG;
    }

    //Method for calculating the total weight of the items
    private double calculateTotalWeight(Map<Long, Integer> itemQuantities) {
        double totalWeight = 0.0;

        for (Map.Entry<Long, Integer> entry : itemQuantities.entrySet()) {
            Long itemId = entry.getKey();
            Integer quantity = entry.getValue();

            // Method to obtain item by ID
            Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with ID " + itemId + " not found"));

            // Calculate the total weight
            totalWeight += item.getWeight() * quantity;
        }

        return totalWeight;
    }

    //Method to manage the logic of "I want 2 items del ID 1"
    public String validateItems(Map<Long, Integer> itemQuantities) {
        double totalWeight = calculateTotalWeight(itemQuantities);

        if (totalWeight > WEIGHT_LIMIT_KG) {
            return "Weight exceeds the allowed limit of " + WEIGHT_LIMIT_TONS + " tonnes.";
        } else {
            return "The total weight is valid and within the permitted limit.";
        }
    }
}
