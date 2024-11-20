package com.example.Spaceship.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Spaceship.models.Category;
import com.example.Spaceship.repositories.CategoryRepository;

@Service
public class CategoryService {

/* From the ‘private final’ to ‘this.categoryRepository = categoryRepository;’, what we do is configure our CategoryRepository
to initialise it as a ‘final’ constant and with its constructor to inject the dependencies of CalculatorRepository
into CategoryService so that we can execute the methods of this class in the database.*/

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
// Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
// Get a category by ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
//Save or update a category
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
//Delete a category by ID using a boolean control structure

/* deleteCategoryById now returns a boolean to indicate whether the category was found and deleted (true),
or if it did not exist (false).and deleted (true), or if it did not exist (false). This makes it easier
to check the success of the operation.*/

    public boolean deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
