package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AmazonService;
import com.alkemy.ong.service.SlidesService;
import com.alkemy.ong.service.mapper.SlidesMapper;
import com.alkemy.ong.util.Base64ToMultipartFile;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlidesServiceImpl implements SlidesService {

    @Autowired
    private SlideRepository slidesRepository;

    @Autowired
    private SlidesMapper slidesMapper;

    @Autowired
    private AmazonService amazonService;
    
    @Autowired
    private MessageSource messageSource;

    @Override
    public SlidesDto createSlides(SlidesDto slidesDto) throws Exception {
        try {
            Slide slides = slidesMapper.slidesDtoToSlides(slidesDto);

            Base64ToMultipartFile decodBase64ToMultipartFile = new Base64ToMultipartFile();
            MultipartFile urlImage = decodBase64ToMultipartFile.base64ToMultipart(slidesDto.getImageUrl());
            String fileUrl = amazonService.uploadFile(urlImage);

            if (slides.getPosition() == null) {
                slides.setPosition(slidesRepository.lastPosition() + 1);
            }
            slides.setImageUrl(fileUrl);
            Slide slideDB = slidesRepository.save(slides);

            return slidesMapper.slidesToSlidesDto(slideDB);

        } catch (Exception e) {
         throw new Exception(messageSource.getMessage("error.created.slide", null, Locale.US));
        }

    }

}
