package com.joe.repository;

import com.joe.dtos.RegisterCategoryDTO;
import com.joe.dtos.ResponseCategoryDTO;
import com.joe.models.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    void addCategory(RegisterCategoryDTO category);
    ResponseCategoryDTO  getCategoryByName(String name);
    List<ResponseCategoryDTO> getCategories();
}
