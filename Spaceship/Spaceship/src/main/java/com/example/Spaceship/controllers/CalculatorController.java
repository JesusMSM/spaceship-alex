package com.example.Spaceship.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Spaceship.models.Calculator;
import com.example.Spaceship.services.CalculatorService;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    //Method to obtain all calculations performed
    @GetMapping
    public List<Calculator> getAllCalculations() {
        return calculatorService.getAllCalculations();
    }

    //Method to obtain a specific calculation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Calculator> getCalculationById(@PathVariable Long id) {
        Calculator calculation = calculatorService.getCalculationById(id);
        return calculation != null ? new ResponseEntity<>(calculation, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Method to create a new calculation (e.g. addition, subtraction, etc.)
    @PostMapping("/calculate")
    public ResponseEntity<Calculator> createCalculation(@RequestBody Calculator calculator) {
        Calculator result = calculatorService.performCalculation(calculator);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    //Method to update an existing calculation
    @PutMapping("/{id}")
    public ResponseEntity<Calculator> updateCalculation(@PathVariable Long id, @RequestBody Calculator calculatorDetails) {
        Calculator updatedCalculator = calculatorService.updateCalculation(id, calculatorDetails);
        return updatedCalculator != null ? new ResponseEntity<>(updatedCalculator, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Method to delete a calculation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        boolean isDeleted = calculatorService.deleteCalculationById(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
