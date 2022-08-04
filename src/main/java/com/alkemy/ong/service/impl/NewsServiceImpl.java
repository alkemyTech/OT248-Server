package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.MemberDto;
import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.Member;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.mapper.NewsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Locale;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired private ModelMapper modelMapper;

    @Autowired private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public NewsDto createNews(NewsDto newsDto) {
        News news = newsMapper.newsDTOtoEntity(newsDto);
        News newNews = newsRepository.save(news);
        return newsMapper.newsEntityToDTO(newNews);
    }


    public NewsDto findNewsById(Long id) {
        return newsMapper.newsEntityToDTO(newsRepository.findById(id).get());
    }

    @Override
    public NewsDto updateNews(NewsDto newsDto) {
        News news = newsMapper.newsDTOtoEntity(newsDto);
        return newsMapper
                .newsEntityToDTO(newsRepository
                        .save(news));
    }

    private NewsDto toDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

}