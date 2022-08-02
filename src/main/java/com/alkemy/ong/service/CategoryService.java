package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.response.CategoryResponseDTO;
import com.alkemy.ong.exception.EmptyListException;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto) throws Exception;

    List<String> getCategoryNames() throws EmptyListException;

    CategoryResponseDTO update (Long id, CategoryDto categoryDto);

}
