package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.response.TestimonialPageResponse;

public interface TestimonialService {
    
    TestimonialDto createTestimonial(TestimonialDto testimonialDto);

    TestimonialDto updateTestimonial(TestimonialDto testimonialDto, Long id);

    TestimonialPageResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteTestimonial(Long id);
    
}