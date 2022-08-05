
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.response.CategoryResponseDTO;
import com.alkemy.ong.model.Category;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CategoryMapper {
    
    
    public CategoryDto categoryToCategoryDTO(Category category){
        return CategoryDto
                .builder()
                .categoyId(category.getCategoyId())
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .createDate(category.getCreateDate())
                .updateDate(category.getUpdateDate())
                .build();
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return Category
                .builder()
                .categoyId(categoryDto.getCategoyId())
                .name(categoryDto.getName())
                .image(categoryDto.getImage())
                .description(categoryDto.getDescription())
                .updateDate(new Date())
                .build();
    }

    public CategoryResponseDTO entityToResponseDTO (Category category){
        return CategoryResponseDTO
                .builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .description(category.getDescription())
                .updateDate(category.getUpdateDate())
                .build();
    }

    public Category updateCategory (Category category, CategoryDto newsFields){

        return Category.builder()
                .id(category.getId())
                .name(newsFields.getName())
                .image(newsFields.getImage())
                .description(newsFields.getDescription())
                .updateDate(new Date())
                .build();
    }

}
