package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.service.mapper.SlideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SlideRepository slideRepository;
    @Autowired
    private SlideMapper slideMapper;

    @Override
    public SlideResponseDTO getById(Long id) {
        Slide slideFound = slideRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Slide " + messageSource.getMessage("not.found", null, Locale.US)));
        return slideMapper.entityToDTO(slideFound);
    }
}
