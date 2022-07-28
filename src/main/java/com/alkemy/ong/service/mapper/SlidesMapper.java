
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.model.Slides;
import org.springframework.stereotype.Component;

@Component
public class SlidesMapper {
    
    public SlidesDto slidesToSlidesDto (Slides slides){
        SlidesDto slidesDto = SlidesDto
                .builder()
                .imageUrl(slides.getImageUrl())
                .text(slides.getText())
                .order(slides.getOrder())
                .build();

        return slidesDto;
    }
    public Slides slidesDtoToSlides (SlidesDto slidesDto){
        Slides slides = Slides
                .builder()
                .imageUrl(slidesDto.getImageUrl())
                .text(slidesDto.getText())
                .order(slidesDto.getOrder())
                .build();

        return slides;
    }
    
}
