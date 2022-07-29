package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.service.mapper.TestimonialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestimonialServiceImpl implements TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper testimonialMapper;

    @Override
    public TestimonialDto createTestimonial(TestimonialDto testimonialDto) {
        Testimonial testimonialEntity = testimonialMapper.testimonialDtoToTestimonial(testimonialDto);
        Testimonial testimonial = testimonialRepository.save(testimonialEntity);
        return testimonialMapper.testimonialToTestimonialDto(testimonial);
    }

}
