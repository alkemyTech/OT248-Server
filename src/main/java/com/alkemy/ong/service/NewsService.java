package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.NewsPageResponse;
import javassist.NotFoundException;

public interface NewsService {

    NewsDto createNews(NewsDto newsDto);
    NewsDto findNewsById(Long id);
    NewsDto updateNews(NewsDto newsDto);
    void deleteById(Long id);
    NewsPageResponse pagination (Integer numberOfPage) throws NotFoundException;
}
