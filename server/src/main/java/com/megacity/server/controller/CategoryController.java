package com.megacity.server.controller;

import com.megacity.server.model.Category;
import com.megacity.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Add a new category
    @PostMapping
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // Get category by ID
    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    // Update category
    @PutMapping("/{categoryId}")
    public Category updateCategory(@PathVariable String categoryId, @RequestBody Category categoryDetails) {
        return categoryService.updateCategory(categoryId, categoryDetails);
    }

    // Delete category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    // Calculate price per kilometer
    @GetMapping("/{categoryId}/calculate-price")
    public double calculatePricePerKm(@PathVariable String categoryId, @RequestParam double distanceInKm) {
        return categoryService.calculatePricePerKm(categoryId, distanceInKm);
    }
}