package com.joe.controller;

import com.joe.dtos.RegisterCategoryDTO;
import com.joe.dtos.ResponseCategoryDTO;
import com.joe.models.Category;
import com.joe.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/categories")
@Validated
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCategory(@Valid @RequestBody RegisterCategoryDTO registerCategoryDTO) {
        try {

            categoryRepository.addCategory(registerCategoryDTO);

            return ResponseEntity.ok("Category added successfully");
        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding category: " + e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<?> getCategories() {
        try {
            List<ResponseCategoryDTO> categories = categoryRepository.getCategories();
            if (categories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No categories found");
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving categories: " + e.getMessage());
        }
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<?> getCategory(@PathVariable String name) {
        try {
            ResponseCategoryDTO category = categoryRepository.getCategoryByName(name);
            if (category == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
            }
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving category: " + e.getMessage());
        }
    }

}