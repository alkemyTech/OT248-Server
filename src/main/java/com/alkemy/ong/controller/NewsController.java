package com.alkemy.ong.controller;

import com.alkemy.ong.dto.NewsDto;
import com.alkemy.ong.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired private NewsService newsService;

    @PostMapping
    public ResponseEntity<NewsDto> createNews(
            @Valid
            @RequestBody NewsDto newsDto
    ){
        return new ResponseEntity<NewsDto>(newsService.createNews(newsDto), HttpStatus.CREATED);
    }
}
