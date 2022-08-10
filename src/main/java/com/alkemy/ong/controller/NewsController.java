package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.dto.response.NewsPageResponse;
import com.alkemy.ong.exception.ApiError;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.service.NewsService;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Locale;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    @GetMapping("/get-all")
    public ResponseEntity<NewsPageResponse> getNewsPaginated (@RequestParam(defaultValue = "1") Integer page) throws NotFoundException {
        return ResponseEntity.ok().body(newsService.pagination(page));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable(name = "id") Long id) {
        try {
            newsService.deleteById(id);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messageSource.getMessage("news.deleted.message", null, Locale.US), HttpStatus.OK);
    }

}
