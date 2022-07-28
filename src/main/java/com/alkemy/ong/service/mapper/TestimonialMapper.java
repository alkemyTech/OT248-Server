
package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import org.springframework.stereotype.Component;

@Component
public class TestimonialMapper {
    
     public TestimonialDto testimonialToTestimonialDto (Testimonial testimonial){
        TestimonialDto testimonialDto = TestimonialDto
                .builder()
                .name(testimonial.getName())
                .image(testimonial.getImage())
                .content(testimonial.getContent())
                .build();

        return testimonialDto;
    }
     public Testimonial testimonialDtoToTestimonial (TestimonialDto testimonialDto){
        Testimonial testimonial = Testimonial
                .builder()
                .name(testimonialDto.getName())
                .image(testimonialDto.getImage())
                .content(testimonialDto.getContent())
                .build();

        return testimonial;
    }
    
    
}
