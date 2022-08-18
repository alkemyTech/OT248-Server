package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.NewsPageResponse;
import com.alkemy.ong.exception.NameAlreadyExists;
import javassist.NotFoundException;

public interface NewsService {

    NewsDto createNews(NewsDto newsDto);
    NewsDto findNewsById(Long id) throws NameAlreadyExists;
    NewsDto updateNews(NewsDto newsDto, Long id);
    void deleteById(Long id);
    NewsPageResponse pagination (Integer numberOfPage) throws NotFoundException;
}
