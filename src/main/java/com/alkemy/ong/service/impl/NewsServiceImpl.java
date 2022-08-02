package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.mapper.NewsMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired private ModelMapper modelMapper;

    @Autowired private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private MessageSource messageSource;


    @Override
    public NewsDto createNews(NewsDto newsDto) {
        return toDto(newsRepository.save(toEntity(newsDto)));
    }


    public NewsDto findNewsById(Long id) {
        return newsMapper.newsEntityToDTO(newsRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News "
                        .concat(messageSource.getMessage("not.found", null, Locale.US))));
        if(!news.isDeleted()){
            newsRepository.deleteById(id);
        }
    }


    private NewsDto toDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

}
