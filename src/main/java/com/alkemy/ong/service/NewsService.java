package com.alkemy.ong.service;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.TestimonialDto;

public interface NewsService {

    public NewsDto createNews(NewsDto newsDto);

    void addTestimonialToNews(TestimonialDto testimonialDto);
}
