package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.mapper.CategoryMapper;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception{
        Category categoryEntity = categoryMapper.CategoryDtoToCategory(categoryDto);
        Category categoryDB= categoryRepository.findByName(categoryEntity.getName());
        if (categoryDB == null) {
            Category category = categoryRepository.save(categoryEntity);
            return categoryMapper.CategoryToCategoryDTO(category);
        }else {
            throw new Exception("There is already a category with this name");
        }
        
        

    }

    @Override
    public CategoryDto findById(Long id) {
        
        Optional<Category> respuesta = categoryRepository.findById(id);
        if (respuesta.isPresent()) {
            Category category = respuesta.get();
            return categoryMapper.CategoryToCategoryDTO(category);
            
        } else {
           throw new EntityNotFoundException(messageSource.getMessage("category.notFound", null, Locale.US));
          
        }
        
       
    }

}
