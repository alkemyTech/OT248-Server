package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.model.Slides;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.AmazonService;
import com.alkemy.ong.service.SlidesService;
import com.alkemy.ong.service.mapper.SlidesMapper;
import java.io.File;
import java.util.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlidesServiceImpl implements SlidesService {

    @Autowired
    private SlidesRepository slidesRepository;

    @Autowired
    SlidesMapper slidesMapper;

    @Autowired
    private AmazonService amazonService;

    @Override
    public SlidesDto createSlides(SlidesDto slidesDto) {

        Slides slides = slidesMapper.slidesDtoToSlides(slidesDto);

        byte[] decodedBytes = Base64.getDecoder().decode(slides.getImageUrl());
        FileUtils.writeByteArrayToFile(new File(outputFileName), decodedBytes);

        if (slides.getOrder() == null) {
            slides.setOrder(slidesRepository.lastOrder() + 1);
        }

        String imageUrl = amazonService.uploadFile(file);
        slides.setImageUrl(imageUrl);

        Slides slidesDB = slidesRepository.save(slides);

        return slidesMapper.slidesToSlidesDto(slidesDB);

    }

}
