package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialDto;
import com.alkemy.ong.dto.response.TestimonialPageResponse;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.service.TestimonialService;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.util.Constants.*;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {
    
    @Autowired
    private TestimonialService testimonialService;
    
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/pagination")
    public TestimonialPageResponse getAllPagination(
            @RequestParam(value = "pageNo", defaultValue = NUMBER_PAGE_DEFAULT, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = SIZE_PAGE_DEFAULT , required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ORDER_BY_DEFAULT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ORDER_DIRECTION_DEFAULT, required = false) String sortDir
    ){
        return testimonialService.getAll(pageNo, pageSize, sortBy, sortDir);
    }
    
    @PostMapping()
    public ResponseEntity<?> createTestimonial(@Valid @RequestBody TestimonialDto testimonialDto,  BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(testimonialService.createTestimonial(testimonialDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editTestimonial(@Valid @RequestBody TestimonialDto testimonialDto, @PathVariable(value = "id") Long id) {
        try {
            TestimonialDto testimonialResponse = testimonialService.updateTestimonial(testimonialDto, id);
            return ResponseEntity
                    .ok()
                    .body(testimonialResponse);
        } catch (Exception exception) {
            throw new ApiError(HttpStatus.NOT_FOUND, exception);
        }
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteTestimonial(@PathVariable Long id) {
        try {
            testimonialService.deleteTestimonial(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(messageSource.getMessage("deleted.testimonial", null, Locale.US), HttpStatus.OK);
    }
}