package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.response.CategoryResponseDTO;
import com.alkemy.ong.exception.EmptyListException;
import com.alkemy.ong.service.CategoryService;

import java.util.List;
import javax.validation.Valid;

import com.alkemy.ong.util.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.alkemy.ong.util.Constants.*;

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
    public ResponseEntity<CategoryDto> categoryDetail(@PathVariable("id") Long id) {
        CategoryDto responseCategoryDto = categoryService.findById(id);
        return ResponseEntity.ok().body(responseCategoryDto);
    }

    @GetMapping
    public CategoryResponse listCategories(
            @RequestParam(value = "pageNo", defaultValue = NUMBER_PAGE_DEFAULT, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = SIZE_PAGE_DEFAULT, required = false) int sizePage,
            @RequestParam(value = "sortBy", defaultValue = ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = ORDER_DIRECTION_DEFAULT, required = false) String sortDir) {
        return categoryService.getAllCategories(numberPage, sizePage, orderBy, sortDir);
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllNames() throws EmptyListException {
        List<String> categoryNames = categoryService.getCategoryNames();
        return ResponseEntity.ok().body(categoryNames);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> update(@PathVariable Long id, @Valid @RequestBody CategoryDto categoryDto) {
        CategoryResponseDTO response = categoryService.update(id, categoryDto);
        return ResponseEntity.ok().body(response);
    }
}