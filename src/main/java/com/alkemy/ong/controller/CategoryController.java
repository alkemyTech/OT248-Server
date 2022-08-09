package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.response.CategoryResponseDTO;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.service.CategoryService;


import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(categoryDto));
        } catch (Exception ex) {
           return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> categoryDetail(@PathVariable("id") Long id){
        CategoryDto responseCategoryDto = categoryService.findById(id);
        return ResponseEntity.ok().body(responseCategoryDto);
    }


    @GetMapping
    public ResponseEntity<List<String>> getAllNames() throws EmptyListException {
        List<String> categoryNames = categoryService.getCategoryNames();
        return ResponseEntity.ok().body(categoryNames);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update (@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto)  {
        CategoryResponseDTO response = categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }



}
