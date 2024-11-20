package com.example.Spaceship.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Item;
import com.example.Spaceship.repositories.ItemRepository;

@Service
public class CalculatorService {

    private final ItemRepository itemRepository;

    // Constants for weight limits
    public static final double WEIGHT_LIMIT_TONS = 150.0; // Maximum weight in tons
    
    // Dynamic weight limit (no longer a static final constant)
    private double weightLimitKg;

    // Constructor that initializes the repository and the default weight limit
    public CalculatorService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.weightLimitKg = WEIGHT_LIMIT_TONS * 1000.0; // Initialize with the default value
    }

    // Method to change the weight limit if necessary
    public void setWeightLimitKg(double weightLimitKg) {
        this.weightLimitKg = weightLimitKg;
    }

    public double getWeightLimitKg() {
        return weightLimitKg;
    }

    // Calculate the total weight of items based on their quantities
    public double calculateTotalWeight(Map<Long, Integer> itemQuantities) {
        double totalWeight = 0.0;

        for (Map.Entry<Long, Integer> entry : itemQuantities.entrySet()) {
            Long itemId = entry.getKey();
            Integer quantity = entry.getValue();

            Item item = itemRepository.findById(itemId).orElse(null);

            if (item == null) {
                System.out.println("Item with ID " + itemId + " not found. Skipping.");
                continue;
            }

            totalWeight += item.getWeight() * quantity;
        }

        return totalWeight;
    }

    // Adjust item quantities to fit within the weight limit
    public Map<Long, Integer> adjustItemsToFitWeightLimit(Map<Long, Integer> itemQuantities) {
        List<Item> items = new ArrayList<>();
    
        // Step 1: Retrieve and add existing items
        for (Map.Entry<Long, Integer> entry : itemQuantities.entrySet()) {
            Long itemId = entry.getKey();
            Item item = itemRepository.findById(itemId).orElse(null);
    
            if (item != null) {
                items.add(item);
            }
        }
    
        // Step 2: Sort items by priority
        items.sort(Comparator.comparing(item -> item.getCategory().getPriority()));
        Collections.reverse(items);

        // Step 3: Adjust quantities to fit the weight limit
        for (Item item : items) {
            Long itemId = item.getId();
            int quantity = itemQuantities.get(itemId);

    
            // Reduce the quantity of the item until the total weight is within the limit
            while (calculateTotalWeight(itemQuantities) > weightLimitKg && quantity > 0) {
                quantity--;
                itemQuantities.put(itemId, quantity); // Update the quantity.
            }
    
            // If the total weight is within the limit, exit the loop
            if (calculateTotalWeight(itemQuantities) <= weightLimitKg) {
                break;
            }
        }
    
        return itemQuantities;
    }

    // Validate total weight and adjust items if necessary
    public String validateAndAdjustItems(Map<Long, Integer> itemQuantities) {
        double totalWeight = calculateTotalWeight(itemQuantities);

        if (totalWeight <= weightLimitKg) {
            return "The total weight is valid and within the permitted limit.";
        }

        Map<Long, Integer> adjustedQuantities = adjustItemsToFitWeightLimit(itemQuantities);
        double newTotalWeight = calculateTotalWeight(adjustedQuantities);

        if (newTotalWeight <= weightLimitKg) {
            return "Items have been adjusted. The new total weight is: " + newTotalWeight + " kg.";
        } else {
            return "Even after adjustments, the total weight exceeds the limit. Further action is required.";
        }
    }
}
