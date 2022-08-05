package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CommentDto;
import com.alkemy.ong.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired private ICommentService iCommentService;

    @PostMapping
    public ResponseEntity<CommentDto> addComment(
            @Valid
            @RequestBody CommentDto commentDto
    ){
        return new ResponseEntity<>(iCommentService.save(commentDto), HttpStatus.CREATED);
    }
}
