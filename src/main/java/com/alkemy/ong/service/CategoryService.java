package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception;

    List<String> getCategoryNames();

}
