package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.model.News;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.service.NewsService;
import com.alkemy.ong.service.mapper.NewsMapper;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired private ModelMapper modelMapper;

    @Autowired private NewsRepository newsRepository;

    @Autowired
    private NewsMapper newsMapper;


    @Override
    public NewsDto createNews(NewsDto newsDto) {
        return toDto(newsRepository.save(toEntity(newsDto)));
    }


    public NewsDto findNewsById(Long id) {
        return newsMapper.newsEntityToDTO(newsRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void deleteById(Long id) throws Exception{
        try {
            Optional<News> news = newsRepository.findById(id);
            if(news.isPresent()){
                newsRepository.deleteById(id);
            }
        } catch (Exception e){
            throw new NotFoundException("news not found");
        }
    }


    private NewsDto toDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

}
