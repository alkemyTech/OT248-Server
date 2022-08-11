package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequestDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> getById(@PathVariable Long id) {
        SlideResponseDTO slideResponse = slideService.getById(id);
        return ResponseEntity.ok().body(slideResponse);
    }

    @PostMapping()
    public ResponseEntity<?> createSlide(@RequestBody SlidesDto slidesDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(slideService.createSlides(slidesDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllSlides() {
        List<SlidesDto> slidesDtos = slideService.getAllSlides();
        return slidesDtos
                .isEmpty() ? new ResponseEntity<>(messageSource.getMessage("error.not.slides", null, Locale.US), HttpStatus.OK)
                : new ResponseEntity<>(slidesDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> update(@PathVariable Long id,
                                                   @RequestBody @Valid SlideRequestDTO request) {
        SlideResponseDTO response = slideService.update(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable(name = "id") Long id) {
        try {
            slideService.deleteById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messageSource.getMessage("slides.deleted.message", null, Locale.US), HttpStatus.OK);
    }
}
