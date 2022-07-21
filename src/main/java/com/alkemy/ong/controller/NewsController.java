package com.alkemy.ong.controller;

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

    @Autowired private INewsService iNewsService;

    @PostMapping
    public ResponseEntity<NewsDto> crear(
            @Valid
            @RequestBody NewsDto newsDto
    ){
        return new ResponseEntity<NewsDto>(iNewsService.crear(newsDto), HttpStatus.CREATED);
    }
}
