package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired private NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto){
        return new ResponseEntity<NewsDto>(newsService.createNews(newsDto), HttpStatus.CREATED);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<NewsDto> detailsNew(@Valid @PathVariable(value = "id") Long id) {
        try {
            NewsDto newsDto = newsService.findNewsById(id);
            return ResponseEntity
                    .ok()
                    .body(newsDto);
        } catch (Exception exception) {
            throw new ApiError(HttpStatus.NOT_FOUND, exception);
        }
    }
}
