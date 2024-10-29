package com.example.Spaceship.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Category;
import com.example.Spaceship.repositories.CategoryRepository;

@Service
public class CategoryService {

/* Desde el "private final" hasta "this.categoryRepository = categoryRepository;",
lo que hacemos es configurar nuestro CategoryRepository para inicializarlo en CategoryService
y podamos ejecutar los metodos de esta clase en la base de datos.*/

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
// 1. Obtener todas las categorías
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
// 2. Obtener una categoría por su ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
// 3. Guardar o actualizar una categoría
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
// 4. Eliminar una categoría por su ID utilizando una estructura de control boolean

/* deleteCategoryById ahora devuelve un boolean para indicar si la categoría fue encontrada
y eliminada (true), o si no existía (false). Esto facilita la comprobación del éxito de la operación.*/

    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
