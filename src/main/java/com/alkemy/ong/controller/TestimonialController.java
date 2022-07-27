
package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.service.TestimonialService;
import com.alkemy.ong.service.mapper.TestimonialMapper;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {
    
    @Autowired
    private TestimonialService testimonialService;
    
    @Autowired
    private TestimonialMapper testimonialMapper;
    
    @PostMapping()
    public ResponseEntity<?> createTestimonial(@Valid @RequestBody TestimonialDto testimonialDto,  BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialDto));
    }
    
    
}