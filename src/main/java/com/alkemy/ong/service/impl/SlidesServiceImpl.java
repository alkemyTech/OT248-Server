package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlidesDto;

import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AmazonService;
import com.alkemy.ong.service.SlidesService;
import com.alkemy.ong.service.mapper.SlidesMapper;
import com.alkemy.ong.util.Base64ToMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SlidesServiceImpl implements SlidesService {


    @Autowired
    private SlideRepository slidesRepository;
    
    @Autowired 
    private ModelMapper modelMapper;

    @Autowired
    private SlidesMapper slidesMapper;

    @Autowired
    private AmazonService amazonService;

    @Override
    public SlidesDto createSlides(SlidesDto slidesDto) {

        Slide slides = slidesMapper.slidesDtoToSlides(slidesDto);

        Base64ToMultipartFile decodBase64ToMultipartFile = new Base64ToMultipartFile();
        MultipartFile urlImage = decodBase64ToMultipartFile.base64ToMultipart(slidesDto.getImageUrl());
        String fileUrl = amazonService.uploadFile(urlImage);
                
        if (slides.getPosition()== null) {
            slides.setPosition(slidesRepository.lastPosition()+ 1);
        }
        slides.setImageUrl(fileUrl);
       Slide slideDB = slidesRepository.save(slides);

        return slidesMapper.slidesToSlidesDto(slideDB);



    @Override
    public List<SlidesDto> getAllSlides() {
        List<Slides> slides = slidesRepository.findAll();
        return slides.stream().map(slide -> toDto(slide)).collect(Collectors.toList());
    }


    private SlidesDto toDto(Slides slides){
        return new SlidesDto(slides.getImageUrl(), slides.getPosition());

    }

}
