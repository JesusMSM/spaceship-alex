package com.example.Spaceship.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Calculator;
import com.example.Spaceship.repositories.CalculatorRepository;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;
    private static final double WEIGHT_LIMIT_TONS = 150.0; //Límite de peso en toneladas
    private static final double WEIGHT_LIMIT_KG = WEIGHT_LIMIT_TONS * 1000.0; //Conversion a kg y límite de peso en kg.

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

    // Método para realizar una operación de cálculo con límite de peso
    public Calculator performCalculationWithWeightLimit(Calculator calculator) {
        // Verificamos si los operandos están dentro del límite de peso
        checkWeightLimit(calculator.getOperand1(), calculator.getOperand2());
        
        // Calculamos el resultado si el peso es adecuado
        double result = calculateResult(calculator);
        calculator.setResult(result);
        
        // Guardamos el cálculo en el repositorio
        return calculatorRepository.save(calculator);
    }

    // Método para verificar el límite de peso
    private void checkWeightLimit(double operand1, double operand2) {
        if (operand1 > WEIGHT_LIMIT_KG || operand2 > WEIGHT_LIMIT_KG) {
            throw new IllegalArgumentException("The operands exceed the allowed weight limit of " + WEIGHT_LIMIT_TONS + " tonnes");
        }
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

    // Método para actualizar un cálculo existente
public Calculator updateCalculation(Long id, Calculator updatedCalculator) {
    // Verificamos si el cálculo existe
    if (!calculatorRepository.existsById(id)) {
        return null;
    }
    
    // Obtenemos el cálculo existente
    Calculator existingCalculator = calculatorRepository.findById(id).orElse(null);
    
    // Actualizamos los valores del cálculo
    if (existingCalculator != null) {
        existingCalculator.setOperand1(updatedCalculator.getOperand1());
        existingCalculator.setOperand2(updatedCalculator.getOperand2());
        existingCalculator.setOperationType(updatedCalculator.getOperationType());
        
        // Volvemos a calcular el resultado y lo actualizamos
        double result = calculateResult(existingCalculator);
        existingCalculator.setResult(result);
        
        // Guardamos el cálculo actualizado en el repositorio
        return calculatorRepository.save(existingCalculator);
    }
    
    return null; // En caso de que no se encuentre el cálculo
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
