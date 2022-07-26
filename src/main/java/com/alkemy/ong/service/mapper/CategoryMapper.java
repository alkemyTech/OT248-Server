
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    
    
    public CategoryDto CategoryToCategoryDTO (Category category){
        CategoryDto categoryDto = CategoryDto
                .builder()
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .build();

        return categoryDto;
    }
    public Category CategoryDtoToCategory (CategoryDto categoryDto){
        Category category = Category
                .builder()
                .name(categoryDto.getName())
                .image(categoryDto.getImage())
                .description(categoryDto.getDescription())
                .build();

        return category;
    }
}
