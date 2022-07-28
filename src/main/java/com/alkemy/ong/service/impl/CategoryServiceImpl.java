package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.mapper.CategoryMapper;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception{
        Category categoryEntity = categoryMapper.CategoryDtoToCategory(categoryDto);
        Category categoryDB= categoryRepository.searchCategoryForName(categoryEntity.getName());
        if (categoryDB == null) {
            Category category = categoryRepository.save(categoryEntity);
            return categoryMapper.CategoryToCategoryDTO(category);
        }else {
            throw new Exception("There is already a category with this name");
        }
        
        

    }

}
