
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {
    
     public TestimonialDto testimonialToTestimonialDto (Testimonial testimonial){
        return TestimonialDto
                .builder()
                .name(testimonial.getName())
                .image(testimonial.getImage())
                .content(testimonial.getContent())
                .build();
    }
     public Testimonial testimonialDtoToTestimonial (TestimonialDto testimonialDto){
        return Testimonial
                .builder()
                .name(testimonialDto.getName())
                .image(testimonialDto.getImage())
                .content(testimonialDto.getContent())
                .build();
    }
    
    
}
