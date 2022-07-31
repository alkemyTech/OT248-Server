package com.alkemy.ong.controller;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById (@PathVariable Long id){
        try {
            SlideResponseDTO slideResponse = slideService.getById(id);
            return ResponseEntity.ok().body(slideResponse);
        } catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
