package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.NewsService;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Locale;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private MessageSource messageSource;

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


    @PutMapping("/{id}")
    public ResponseEntity<NewsDto> updateNews (@Valid @PathVariable(value = "id") Long id, @RequestBody NewsDto newsUpdate ) {
        try {
            NewsDto newsDto = newsService.findNewsById(id);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        NewsDto newsDtoResponse = newsService.updateNews(newsUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(newsDtoResponse);
    }








}
