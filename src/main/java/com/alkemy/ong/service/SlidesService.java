

package com.alkemy.ong.service;

import com.alkemy.ong.dto.SlidesDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface SlidesService {
    
    public SlidesDto createSlides(SlidesDto slidesDto);

    public List<SlidesDto> getAllSlides();

}
