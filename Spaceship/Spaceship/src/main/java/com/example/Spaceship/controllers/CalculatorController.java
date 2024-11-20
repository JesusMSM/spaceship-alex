package com.example.Spaceship.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spaceship.services.CalculatorService;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    // Constructor to inject CalculatorService
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    //Endpoint to validate and adjust item weights
    @PostMapping("/validate")
    public ResponseEntity<String> validateAndAdjustItems(@RequestBody Map<Long, Integer> itemQuantities) {
        try {
            // Call the service's validateAndAdjustItems method
            String validationMessage = calculatorService.validateAndAdjustItems(itemQuantities);

            // If the total weight is within the permitted limit
            if (validationMessage.contains("within the permitted limit")) {
                return ResponseEntity.ok(validationMessage);
            } else {
                // If adjustments were made or weight still exceeds the limit
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessage);
            }
        } catch (IllegalArgumentException e) {
            // Handle the case where an item ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Generic error handling
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}
