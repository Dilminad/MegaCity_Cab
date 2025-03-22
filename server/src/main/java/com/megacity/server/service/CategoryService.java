package com.megacity.server.service;

import com.megacity.server.model.Category;
import com.megacity.server.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Add a new category
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Get all categories
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get category by ID
    public Category getCategoryById(String categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    // Update category
    public Category updateCategory(String categoryId, Category categoryDetails) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
            category.setModel(categoryDetails.getModel());
            category.setDescription(categoryDetails.getDescription());
            category.setPrice(categoryDetails.getPrice());
            category.setActive(categoryDetails.isActive());
            return categoryRepository.save(category);
        }
        return null;
    }

    // Delete category
    public void deleteCategory(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    // Calculate price per kilometer
    public double calculatePricePerKm(String categoryId, double distanceInKm) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category != null) {
            return category.getPrice() * distanceInKm;
        }
        return 0.0;
    }
}