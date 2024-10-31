package com.example.Spaceship.controllers;

import com.example.Spaceship.models.Calculator;
import com.example.Spaceship.services.CalculatorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    // Método para obtener todos los cálculos realizados
    @GetMapping
    public List<Calculator> getAllCalculations() {
        return calculatorService.getAllCalculations();
    }

    // Método para obtener un cálculo específico por ID
    @GetMapping("/{id}")
    public ResponseEntity<Calculator> getCalculationById(@PathVariable Long id) {
        Calculator calculation = calculatorService.getCalculationById(id);
        return calculation != null ? new ResponseEntity<>(calculation, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método para crear un nuevo cálculo (por ejemplo, suma, resta, etc.)
    @PostMapping("/calculate")
    public ResponseEntity<Calculator> createCalculation(@RequestBody Calculator calculator) {
        Calculator result = calculatorService.performCalculation(calculator);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Método para actualizar un cálculo existente
    @PutMapping("/{id}")
    public ResponseEntity<Calculator> updateCalculation(@PathVariable Long id, @RequestBody Calculator calculatorDetails) {
        Calculator updatedCalculator = calculatorService.updateCalculation(id, calculatorDetails);
        return updatedCalculator != null ? new ResponseEntity<>(updatedCalculator, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método para eliminar un cálculo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id) {
        boolean isDeleted = calculatorService.deleteCalculationById(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
