package com.example.Spaceship.services;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Spaceship.models.Category;
import com.example.Spaceship.models.Item;
import com.example.Spaceship.repositories.ItemRepository;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    private ItemRepository itemRepository; // Mocks data access.

    @InjectMocks
    private CalculatorService calculatorService; // Class under test.

    private Category lifeSupport; // Category: Life Support.
    private Category scientificEquipment; // Category: Scientific Equipment.
    private Item oxygenTank; // Oxygen Tank.
    private Item researchKit; // Research Kit.

    @BeforeEach
    void setUp() {
        // Set up categories and items.
        lifeSupport = new Category("Life Support");
        scientificEquipment = new Category("Scientific Equipment");

        oxygenTank = new Item(1L, "Oxygen Tank", 50.0, "Critical for breathing", lifeSupport);
        researchKit = new Item(2L, "Research Kit", 20.0, "For scientific experiments", scientificEquipment);
    }

    // 1. Test to verify that the total weight is correct.
    @Test
    void testCalculateTotalWeight_CorrectWeight() {
        Map<Long, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put(1L, 2); // 2 oxygen tanks.
        itemQuantities.put(2L, 1); // 1 research kit.

        when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(oxygenTank));
        when(itemRepository.findById(2L)).thenReturn(java.util.Optional.of(researchKit));

        double totalWeight = calculatorService.calculateTotalWeight(itemQuantities);

        // Expected weight: (2 * 50.0) + (1 * 20.0) = 120.0
        assertEquals(120.0, totalWeight);

        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).findById(2L);
    }

    // 2. Test to verify that the total weight exceeds the limit.
    @Test
    void testCalculateTotalWeight_ExceedsLimit() {
        Map<Long, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put(1L, 3); // 3 oxygen tanks.
        itemQuantities.put(2L, 5); // 5 research kits.

        when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(oxygenTank));
        when(itemRepository.findById(2L)).thenReturn(java.util.Optional.of(researchKit));

        double totalWeight = calculatorService.calculateTotalWeight(itemQuantities);

        // Expected weight: (3 * 50.0) + (5 * 20.0) = 250.0
        assertEquals(250.0, totalWeight);

        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).findById(2L);
    }

    // 3. Test when an item is not found in the repository.
    @Test
    void testCalculateTotalWeight_ItemNotFound() {
        Map<Long, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put(3L, 2); // Item with non-existing ID.

        when(itemRepository.findById(3L)).thenReturn(java.util.Optional.empty());

        double totalWeight = calculatorService.calculateTotalWeight(itemQuantities);

        // Expected weight: 0.0, as the item is not found.
        assertEquals(0.0, totalWeight);

        verify(itemRepository, times(1)).findById(3L);
    }

    // 4. Test to verify that lower priority items are removed if the weight exceeds the limit.
    @Test
    void testAdjustItemsToFitWeightLimit_ItemsRemovedToFitLimit() {
        // Set a lower weight limit to simplify the test
        calculatorService.setWeightLimitKg(100.0);  // Set the weight limit to 100 kg

        Map<Long, Integer> itemQuantities = new HashMap<>();
        itemQuantities.put(1L, 2); // 2 oxygen tanks.
        itemQuantities.put(2L, 3); // 3 research kits.

        // Set category priorities.
        lifeSupport.setPriority(1L); // High priority.
        scientificEquipment.setPriority(2L); // Low priority.

        // Simulate item lookup in the repository.
        when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(oxygenTank));
        when(itemRepository.findById(2L)).thenReturn(java.util.Optional.of(researchKit));

        // Run the method to adjust the quantities.
        Map<Long, Integer> adjustedQuantities = calculatorService.adjustItemsToFitWeightLimit(itemQuantities);

        // Verify the adjusted weight.
        double adjustedWeight = calculatorService.calculateTotalWeight(adjustedQuantities);

        // The adjusted weight should be within the limit.
        assertEquals(100.0, adjustedWeight, 0.01);

        // Verify that lower priority items were removed.
        assertEquals(2, adjustedQuantities.get(1L)); // Oxygen tanks remain.
        assertEquals(0, adjustedQuantities.get(2L)); // Research kits reduced.

        // Checks how many times the id is invoked, it is a process that encourages coding efficiency.
        verify(itemRepository, times(7)).findById(1L);
        verify(itemRepository, times(7)).findById(2L);
    }
}
