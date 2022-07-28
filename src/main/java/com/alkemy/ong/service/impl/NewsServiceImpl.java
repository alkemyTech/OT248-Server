package com.alkemy.ong.service.impl;


import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.NewsRepository;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired private ModelMapper modelMapper;

    @Autowired private NewsRepository newsRepository;

    @Autowired private TestimonialRepository testimonialRepository;


    @Override
    public NewsDto createNews(NewsDto newsDto) {
        return toDto(newsRepository.save(toEntity(newsDto)));
    }

    @Override
    public void addTestimonialToNews(TestimonialDto testimonialDto) {
        testimonialRepository.save(testimonialDtoToEntity(testimonialDto));
    }




    private NewsDto toDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }

    private News toEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

    private TestimonialDto testimonialToDto(Testimonial testimonial){
        return modelMapper.map(testimonial, TestimonialDto.class);
    }

    private Testimonial testimonialDtoToEntity(TestimonialDto testimonialDto){
        return modelMapper.map(testimonialDto, Testimonial.class);
    }


}
