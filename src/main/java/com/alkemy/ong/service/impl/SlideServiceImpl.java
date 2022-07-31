package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.SlideRepository;
import com.alkemy.ong.service.SlideService;
import com.alkemy.ong.service.mapper.SlideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class SlideServiceImpl implements SlideService {

    @Autowired
    SlideRepository slideRepository;
    @Autowired
    SlideMapper slideMapper;

    @Override
    public SlideResponseDTO getById(Long id) {
        Optional<Slide> slideFound = slideRepository.findById(id);
        if (slideFound.isEmpty()) throw new EntityNotFoundException("Slide with that id not found");
        return slideMapper.entityToDTO(slideFound.get());
    }
}
