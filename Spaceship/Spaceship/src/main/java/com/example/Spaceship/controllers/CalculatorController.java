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

    //Method to validate
    @PostMapping("/validate")
    public ResponseEntity<String> validateItems(@RequestBody Map<Long, Integer> itemQuantities) {
        try {
            // Call the service's validateItems method to get the result.
            String validationMessage = calculatorService.validateItems(itemQuantities);

            // If the message indicates that the weight is valid, it returns a code 200 (OK).
            if (validationMessage.contains("within the permitted limit")) {
                return ResponseEntity.ok(validationMessage);
            } else {
                // If the weight exceeds the limit, a code 400 (Bad Request) is returned.
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessage);
            }
        } catch (IllegalArgumentException e) {
            // If there is an item not found, it is handled with a 404 (Not Found) code.
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            // Generic exception handling for unexpected errors.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred: " + e.getMessage());
        }
    }
}
