
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.repository.SlidesRepository;
import com.alkemy.ong.service.SlidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlidesServiceImpl implements SlidesService{

    @Autowired
    private SlidesRepository SlidesRepository;
    
    @Autowired
    private AmazonServiceImpl amazonServiceImpl;
    
    @Override
    public SlidesDto createSlides(SlidesDto slidesDto) {
       
        
        
    }
    
}
