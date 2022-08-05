package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.News;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class NewsMapper {

    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryMapper categoryMapper;

    public NewsDto newsEntityToDTO (News news) {
        return NewsDto
                .builder()
                .id(news.getId())
                .content(news.getContent())
                .name(news.getName())
                .image(news.getImage())
                .categoryId(news.getCategoryId().getCategoyId())
                .createDate(news.getCreateDate())
                .updateDate(news.getUpdateDate())
                .build();
    }

    public News newsDTOtoEntity (NewsDto newsDto) {
        return News
                .builder()
                .id(newsDto.getId())
                .content(newsDto.getContent())
                .name(newsDto.getName())
                .image(newsDto.getImage())
                .categoryId(categoryMapper.CategoryDtoToCategory(
                        categoryService.findById(newsDto.getCategoryId())))
                .createDate(newsDto.getCreateDate())
                .updateDate(new Date())
                .build();
    }

}
