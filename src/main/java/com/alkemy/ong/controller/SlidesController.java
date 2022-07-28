package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.service.SlidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
public class SlidesController {

    @Autowired
    private SlidesService slidesService;

    @PostMapping()
    public ResponseEntity<?> createSlide(@RequestBody SlidesDto slidesDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(slidesService.createSlides(slidesDto));

    }
}
