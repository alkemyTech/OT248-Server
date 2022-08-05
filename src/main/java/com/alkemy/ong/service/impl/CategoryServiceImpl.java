package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.CategoryService;
import com.alkemy.ong.service.mapper.CategoryMapper;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Locale;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) throws Exception {
        Category categoryEntity = categoryMapper.CategoryDtoToCategory(categoryDto);
        Category categoryDB = categoryRepository.findByName(categoryEntity.getName());
        if (categoryDB == null) {
            Category category = categoryRepository.save(categoryEntity);
            return categoryMapper.CategoryToCategoryDTO(category);
        } else {
            throw new AlreadyExistsException(messageSource.getMessage("error.category.already.exists", null, Locale.US));
        }
    }

    public List<String> getCategoryNames() throws EmptyListException {
       if (categoryRepository.findAllCategoryNames().isEmpty())
           throw new EmptyListException(messageSource.getMessage("error.categorylist.empty", null, Locale.US));
       return categoryRepository.findAllCategoryNames();
    }

    @Override
    public CategoryDto findById(Long id) {
        
        Optional<Category> res = categoryRepository.findById(id);
        if (res.isPresent()) {
            Category category = res.get();
            return categoryMapper.CategoryToCategoryDTO(category);
            
        } else {
           throw new EntityNotFoundException(messageSource.getMessage("category.notFound", null, Locale.US));
          
        }
        
       
    }

}
