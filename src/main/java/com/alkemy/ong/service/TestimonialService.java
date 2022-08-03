package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;

public interface TestimonialService {
    
    public TestimonialDto createTestimonial(TestimonialDto testimonialDto);
    public TestimonialDto updateTestimonial(TestimonialDto testimonialDto, Long id);
}