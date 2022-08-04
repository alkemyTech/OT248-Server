
package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDto;
import org.springframework.web.multipart.MultipartFile;


public interface SlidesService {
    
    public SlidesDto createSlides(SlidesDto slidesDto) throws Exception;
}
