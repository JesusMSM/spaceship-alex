package com.example.Spaceship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spaceship.models.Calculator;

@Repository
public interface CalculatorRepository extends JpaRepository<Calculator, Long>{
        
}