package com.example.Spaceship.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Calculator;
import com.example.Spaceship.repositories.CalculatorRepository;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    //Constructor para inyectar la dependencias de CalculatorRepository
    public CalculatorService(CalculatorRepository calculatorRepository){
        this.calculatorRepository = calculatorRepository;
    }

    //Método para obtener todos los calculos
    public List<Calculator>getAllCalculations(){
        return calculatorRepository.findAll();
    }

    // Método para obtener un cálculo por su ID
    public Calculator getCalculationById(Long id) {
        return calculatorRepository.findById(id).orElse(null);
    }

    //Método para realizar una operación de cálculo utilizando el método calculateResult y guardar el resultado
    public Calculator performCalculation(Calculator calculator) {
        double result = calculateResult(calculator);
        calculator.setResult(result);
        return calculatorRepository.save(calculator);
    }
    
    // Método privado para calcular el resultado en función del tipo de operación
    private double calculateResult(Calculator calculator){
        double operand1 = calculator.getOperand1();
        double operand2 = calculator.getOperand2();
        String operationType = calculator.getOperationType();

// switch para calcular el tipo de operacion y lanzar excepciones en caso de que no se pueda dividir o no exista el tipo de operación.
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

    //Método para eliminar un cálculo por su ID
    public boolean deleteCalculationById(Long id){
        if (calculatorRepository.existsById(id)) {
            calculatorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
