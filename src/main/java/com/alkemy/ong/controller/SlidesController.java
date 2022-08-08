package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlidesDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/slides")
public class SlidesController {
    
    @Autowired
    private SlideService slidesService;

    @PostMapping()
    public ResponseEntity<?> createSlide(@RequestBody SlidesDto slidesDto){
        
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(slidesService.createSlides(slidesDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<?> getAllSlides(){
        List<SlidesDto> slidesDtos = slidesService.getAllSlides();
        return slidesDtos
                .isEmpty() ? new ResponseEntity<>("no slides yet", HttpStatus.OK)
                : new ResponseEntity<>(slidesDtos, HttpStatus.OK);

    }
}
