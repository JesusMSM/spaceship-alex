package com.example.Spaceship.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    // Method to sort items by category priority
    private List<Item> sortItemsByPriority(Map<Long, Integer> itemQuantities) {
        List<Item> items = new ArrayList<>();

        // Get the list of items from Map
        for (Long itemId : itemQuantities.keySet()) {
            Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Item with ID " + itemId + " not found"));
            items.add(item);
    }

        // Sort the list of items by category priority using Comparator
        items.sort(new Comparator<Item>() {

            @Override
            public int compare(Item item1, Item item2) {
                return Long.compare(item1.getCategory().getPriority(), item2.getCategory().getPriority());
            }
        });
        return items;

    }

    // Method to validate items and handle weight exceedance
    public String validateItems(Map<Long, Integer> itemQuantities) {
        // Calculate the total weight of the items
        double totalWeight = calculateTotalWeight(itemQuantities);

        // If the total weight is within the allowed limit, return a success message
        if (totalWeight <= WEIGHT_LIMIT_KG) {
            return "The total weight is valid and within the permitted limit.";
        }

        // Build the initial response message indicating weight exceedance
        StringBuilder response = new StringBuilder();
        response.append("Weight exceeds the allowed limit of ").append(WEIGHT_LIMIT_TONS).append(" tonnes.");

        // Get items sorted by category priority
        List<Item> sortedItems = sortItemsByPriority(itemQuantities);
        response.append("Here are the items sorted by priority:");

        // Display each item's details: name, priority, and weight
        for (Item item : sortedItems) {
            response.append("Item: ").append(item.getName())
                    .append(", Category Priority: ").append(item.getCategory().getPriority())
                    .append(", Weight per Unit: ").append(item.getWeight()).append(" kg");
        }

        response.append("Please remove items according to their priority to fit within the weight limit.");

        // Simulate the removal of items until the total weight is within the limit
        for (Item item : sortedItems) {
            Long itemId = item.getId();
            int quantity = itemQuantities.get(itemId);
            
            // Keep removing items one by one as long as the weight exceeds the limit
            while (quantity > 0 && calculateTotalWeight(itemQuantities) > WEIGHT_LIMIT_KG) {
                quantity--; // Decrease the quantity of the current item
                itemQuantities.put(itemId, quantity); // Update the quantity in the map
            }
        }

        // Recalculate the total weight after adjustments
        double newTotalWeight = calculateTotalWeight(itemQuantities);
        
        // Check if the adjusted weight is now within the limit
        if (newTotalWeight <= WEIGHT_LIMIT_KG) {
            response.append("Items adjusted. The new total weight is within the allowed limit: ")
                    .append(newTotalWeight).append(" kg.");
        } else {
            response.append("Even after adjustments, the weight still exceeds the limit. Further action required.");
        }

        return response.toString();
    }
}