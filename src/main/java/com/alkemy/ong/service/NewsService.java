package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;

public interface NewsService {

    NewsDto createNews(NewsDto newsDto);

    NewsDto findNewsById(Long id);
}
