package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlideRequestDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.SlideResponseDTO;

import java.util.List;

public interface SlideService {

    SlideResponseDTO getById(Long id);

    List<SlidesDto> getAllSlides();

    List<SlideResponseDTO> getAllSlidesById(Long id);
    
    SlidesDto createSlides(SlidesDto slidesDto) throws Exception;

    void deleteById(Long id);

    SlideResponseDTO update (Long id, SlideRequestDTO requestDTO);

}
