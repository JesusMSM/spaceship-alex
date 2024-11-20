package com.example.Spaceship.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Calculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Notation @Column(nullable = false): Makes the value of operationType mandatory, so null is not allowed.
    @Column(nullable = false)
    private String operationType;

    @Column(nullable = false)
    private double operand1;

    @Column(nullable = false)
    private double operand2;

    /* ‘result’ does not have the @Column(nullable = false) annotation because the result
    is produced by calculating operand1, operand2, and operationType. */
    private double result;

    public Calculator(){

    }

    public Calculator (String operationType, double operand1, double operand2, double result){
        this.operationType = operationType;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getOperationType(){
        return operationType;
    }

    public void setOperationType(String operationType){
        this.operationType = operationType;
    }

    public double getOperand1(){
        return operand1;
    }

    public void setOperand1(double operand1){
        this.operand1 = operand1;
    }

    public double getOperand2(){
        return operand2;
    }

    public void setOperand2(double operand2){
        this.operand2 = operand2;
    }

    public double getResult(){
        return result;
    }

    public void setResult(double result){
        this.result = result;
    }

}
