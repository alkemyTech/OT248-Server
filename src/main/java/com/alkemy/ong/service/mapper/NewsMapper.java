package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.NewsPageResponse;
import com.alkemy.ong.dto.response.NewsResponse;
import com.alkemy.ong.model.News;
import com.alkemy.ong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                .categoryId(news.getCategoryId().getId())
                .createDate(news.getCreateDate())
                .updateDate(news.getUpdateDate())
                .build();
    }

    public News newsDTOtoEntity (NewsDto newsDto) {
        return News
                .builder()
                .content(newsDto.getContent())
                .name(newsDto.getName())
                .image(newsDto.getImage())
                .categoryId(categoryMapper.categoryDtoToCategory(
                        categoryService.findById(newsDto.getCategoryId())))
                .createDate(newsDto.getCreateDate())
                .updateDate(new Date())
                .build();
    }

    public News newsDTOtoEntity (NewsDto newsDto, Long id) {
        return News
                .builder()
                .id(id)
                .content(newsDto.getContent())
                .name(newsDto.getName())
                .image(newsDto.getImage())
                .categoryId(categoryMapper.categoryDtoToCategory(
                        categoryService.findById(newsDto.getCategoryId())))
                .createDate(newsDto.getCreateDate())
                .updateDate(new Date())
                .build();
    }

    public NewsResponse entityToResponse (News newEntity){
        return NewsResponse.builder()
                .id(newEntity.getId())
                .name(newEntity.getName())
                .image(newEntity.getImage())
                .content(newEntity.getContent())
                .categoryId(newEntity.getCategoryId().getId())
                .createDate(newEntity.getCreateDate())
                .build();
    }

    public List<NewsResponse> entityListToResponseList (List<News> newsList){
        List<NewsResponse> newsResponseList = new ArrayList<>();
        for (News newEntity : newsList){
            newsResponseList.add(entityToResponse(newEntity));
        }

        return newsResponseList;
    }

    public NewsPageResponse entityPageToPageResponse (List<News> members, String previous, String next){
        return NewsPageResponse.builder()
                .news(entityListToResponseList(members))
                .previous(previous)
                .next(next)
                .build();
    }

}
