package com.alkemy.ong.controller;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSlide(@PathVariable(name = "id") Long id){

        return null;
    }
}
