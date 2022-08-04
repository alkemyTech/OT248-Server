package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;

public interface NewsService {

    public NewsDto createNews(NewsDto newsDto);
    NewsDto findNewsById(Long id);
    NewsDto updateNews(NewsDto newsDto);
}
