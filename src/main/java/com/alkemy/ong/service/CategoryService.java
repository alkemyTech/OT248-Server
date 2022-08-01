package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
    
    public CategoryDto findById(Long id);
}
