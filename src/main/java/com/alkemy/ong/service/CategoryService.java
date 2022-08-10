package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.response.CategoryResponseDTO;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.util.CategoryResponse;
import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;
     CategoryDto findById(Long id);
    CategoryResponse getAllCategories(int numPage, int sizePage, String orderBy, String sortDir);
    List<String> getCategoryNames() throws EmptyListException;
    CategoryResponseDTO update (Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}