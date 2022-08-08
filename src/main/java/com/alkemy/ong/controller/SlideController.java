package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequestDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    private SlideService slideService;

    @GetMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> getById (@PathVariable Long id){
            SlideResponseDTO slideResponse = slideService.getById(id);
            return ResponseEntity.ok().body(slideResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllSlides(){
        List<SlidesDto> slidesDtos = slideService.getAllSlides();
        return slidesDtos
                .isEmpty() ? new ResponseEntity<>("no slides yet", HttpStatus.OK)
                : new ResponseEntity<>(slidesDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SlideResponseDTO> update (@PathVariable Long id,
                                                    @RequestBody @Valid SlideRequestDTO request){
        SlideResponseDTO response = slideService.update(id, request);
        return ResponseEntity.ok().body(response);
    }

}
