package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.SlideResponseDTO;

import java.util.List;

public interface SlideService {

    SlideResponseDTO getById(Long id);

    List<SlidesDto> getAllSlides();

}
