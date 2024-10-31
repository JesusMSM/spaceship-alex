package com.example.Spaceship.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spaceship.models.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
