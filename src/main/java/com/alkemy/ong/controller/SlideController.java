package com.alkemy.ong.controller;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> getById (@PathVariable Long id){
            SlideResponseDTO slideResponse = slideService.getById(id);
            return ResponseEntity.ok().body(slideResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable(name = "id") Long id){
        try {
            slideService.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messageSource.getMessage("slides.deleted.message", null, Locale.US), HttpStatus.OK);
    }
}
