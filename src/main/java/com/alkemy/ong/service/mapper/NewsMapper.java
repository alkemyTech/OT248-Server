package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.News;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public NewsDto newsEntityToDTO (News news) {
        NewsDto newsDto = NewsDto
                .builder()
                .content(news.getContent())
                .name(news.getName())
                .image(news.getImage())
                .build();
        return newsDto;
    }

}
