package com.example.Spaceship.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Calculator;
import com.example.Spaceship.repositories.CalculatorRepository;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;
    private static final double WEIGHT_LIMIT_TONS = 150.0; //Weight limit in tonnes
    private static final double WEIGHT_LIMIT_KG = WEIGHT_LIMIT_TONS * 1000.0; //Conversion to kg and weight limit in kg

    //Constructor for injecting CalculatorRepository dependencies
    public CalculatorService(CalculatorRepository calculatorRepository){
        this.calculatorRepository = calculatorRepository;
    }

    //Method to obtain all calculations
    public List<Calculator>getAllCalculations(){
        return calculatorRepository.findAll();
    }

    //Method to obtain a calculation by ID
    public Calculator getCalculationById(Long id) {
        return calculatorRepository.findById(id).orElse(null);
    }

    //Method for performing a calculation operation with a weight limit
    public Calculator performCalculationWithWeightLimit(Calculator calculator) {
        //Check if the operands are within the weight limit.
        checkWeightLimit(calculator.getOperand1(), calculator.getOperand2());
        
        //Calculate the result if the weight is adequate
        double result = calculateResult(calculator);
        calculator.setResult(result);
        
        //Save the calculation in the repository
        return calculatorRepository.save(calculator);
    }

    //Method for verifying the weight limit
    private void checkWeightLimit(double operand1, double operand2) {
        if (operand1 > WEIGHT_LIMIT_KG || operand2 > WEIGHT_LIMIT_KG) {
            throw new IllegalArgumentException("The operands exceed the allowed weight limit of " + WEIGHT_LIMIT_TONS + " tonnes");
        }
    }

    //Method for performing a calculation operation using the calculateResult method and saving the result
    public Calculator performCalculation(Calculator calculator) {
        double result = calculateResult(calculator);
        calculator.setResult(result);
        return calculatorRepository.save(calculator);
    }
    
    //Private method for calculating the result depending on the type of transaction
    private double calculateResult(Calculator calculator){
        double operand1 = calculator.getOperand1();
        double operand2 = calculator.getOperand2();
        String operationType = calculator.getOperationType();

//switch to calculate the operation type and throw exceptions in case the operation type cannot be split or does not exist
        switch (operationType){
            case "sum" -> {
                return operand1 + operand2;
            }
            case "minus" -> {
                return operand1 - operand2;
            }
            case "multiply" -> {
                return operand1 * operand2;
            }
            case "divide" -> {
                if (operand2 != 0){
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("cannot divide by zero");
                }
            }
            default -> throw new IllegalArgumentException("Unknown operation type: "+ operationType);
        }
    }

    //Method for updating an existing calculation
public Calculator updateCalculation(Long id, Calculator updatedCalculator) {
    //check if the calculation exists
    if (!calculatorRepository.existsById(id)) {
        return null;
    }
    
    //Obtain the existing calculation
    Calculator existingCalculator = calculatorRepository.findById(id).orElse(null);
    
    //Update the values of the calculation
    if (existingCalculator != null) {
        existingCalculator.setOperand1(updatedCalculator.getOperand1());
        existingCalculator.setOperand2(updatedCalculator.getOperand2());
        existingCalculator.setOperationType(updatedCalculator.getOperationType());
        
        //Recalculate the result and update it.
        double result = calculateResult(existingCalculator);
        existingCalculator.setResult(result);
        
        //Save the updated calculation in the repository
        return calculatorRepository.save(existingCalculator);
    }
    
    return null; //In case the calculation is not found
}

    //Method to delete a calculation by ID
    public boolean deleteCalculationById(Long id){
        if (calculatorRepository.existsById(id)) {
            calculatorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
