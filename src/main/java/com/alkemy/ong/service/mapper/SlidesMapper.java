
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.model.Slide;

import org.springframework.stereotype.Component;

@Component
public class SlidesMapper {

    public SlidesDto slidesToSlidesDto(Slide slides) {
        SlidesDto slidesDto = SlidesDto
                .builder()
                .imageUrl(slides.getImageUrl())
                .order(slides.getPosition())
                .build();

        return slidesDto;
    }

    public Slide slidesDtoToSlides(SlidesDto slidesDto) {
        Slide slides = Slide
                .builder()
                .imageUrl(slidesDto.getImageUrl())                
                .position(slidesDto.getOrder())
                .build();

        return slides;
    }

}
