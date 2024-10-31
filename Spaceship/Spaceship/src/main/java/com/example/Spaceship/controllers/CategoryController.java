package com.example.Spaceship.controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.Spaceship.models.Category;
import com.example.Spaceship.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    // Constructor con inyección de dependencia de CategoryService.
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    
     //Obtener todas las categorías.
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

     // Obtener una categoría específica por ID.
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
     //Crear una nueva categoría.
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

     //Actualizar una categoría existente.

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Optional<Category> categoryData = categoryService.getCategoryById(id);

        if (categoryData.isPresent()) {
            Category category = categoryData.get();
            category.setName(categoryDetails.getName()); // Actualización de nombre (puedes agregar más campos si es necesario)
            Category updatedCategory = categoryService.saveCategory(category);
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

     //Eliminar una categoría por su ID.
    
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable Long id) {
        boolean isDeleted = categoryService.deleteCategoryById(id);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



