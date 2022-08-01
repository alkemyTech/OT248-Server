package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.SlidesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SlidesServiceImpl implements SlidesService {

    @Autowired private ModelMapper modelMapper;

    @Autowired private SlidesRepository slidesRepository;

    @Override
    public List<SlidesDto> getAllSlides() {
        List<Slides> slides = slidesRepository.findAll();
        return slides.stream().map(slide -> toDto(slide)).collect(Collectors.toList());
    }


    private SlidesDto toDto(Slides slides){
        return modelMapper.map(slides, SlidesDto.class);
    }

    private Slides toEntity(SlidesDto slidesDto){
        return modelMapper.map(slidesDto, Slides.class);
    }

}
