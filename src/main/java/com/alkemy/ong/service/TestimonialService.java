package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {
    
    TestimonialDto createTestimonial(TestimonialDto testimonialDto);

    TestimonialDto updateTestimonial(TestimonialDto testimonialDto, Long id);
    
}