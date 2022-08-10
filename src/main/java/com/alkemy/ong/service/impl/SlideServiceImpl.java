package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlideRequestDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.AmazonService;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.service.mapper.SlideMapper;
import com.alkemy.ong.service.mapper.SlidesMapper;
import com.alkemy.ong.util.Base64ToMultipartFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private SlideMapper slideMapper;
    
    @Autowired
    private SlidesMapper slidesMapper;

    @Autowired
    private AmazonService amazonService;

    @Override
    public SlideResponseDTO getById(Long id) {
        Slide slideFound = slideRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Slide " + messageSource.getMessage("not.found", null, Locale.US)));
        return slideMapper.entityToDTO(slideFound);
    }

    @Override
    public List<SlidesDto> getAllSlides() {
        List<Slide> slides = slideRepository.findAll();
        return slides.stream().map(slide -> toDto(slide)).collect(Collectors.toList());
    }

    @Override
    public SlideResponseDTO update(Long id, SlideRequestDTO requestDTO) {
        Slide slide = slideRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage("slide.not.found", null, Locale.US)));

        slide = slideMapper.updateSlide(requestDTO, slide);
        Slide slideUpdated = slideRepository.save(slide);

        return slideMapper.entityToDTO(slideUpdated);
    }
    public void deleteById(Long id) {
        Slide slide = slideRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Slide ".concat(messageSource.getMessage("not.found", null, Locale.US))));
        slideRepository.delete(slide);
    }

    private SlidesDto toDto(Slide slides){
        return new SlidesDto(slides.getImageUrl(), slides.getPosition());
    }
    
     @Override
    public SlidesDto createSlides(SlidesDto slidesDto) throws Exception {
        try {
            Slide slides = slidesMapper.slidesDtoToSlides(slidesDto);

            Base64ToMultipartFile decodBase64ToMultipartFile = new Base64ToMultipartFile();
            MultipartFile urlImage = decodBase64ToMultipartFile.base64ToMultipart(slidesDto.getImageUrl());
            String fileUrl = amazonService.uploadFile(urlImage);

            if (slides.getPosition() == null) {
                slides.setPosition(slideRepository.lastPosition() + 1);
            }
            slides.setImageUrl(fileUrl);
            Slide slideDB = slideRepository.save(slides);

            return slidesMapper.slidesToSlidesDto(slideDB);

        } catch (Exception e) {
         throw new Exception(messageSource.getMessage("error.created.slide", null, Locale.US));
        }

    }
}
